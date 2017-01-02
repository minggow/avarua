package com.mingguo.avarua.casual.account.test.common.mess.concurrent;

import lombok.Data;
import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.Immutable;
import org.apache.http.annotation.ThreadSafe;
import org.junit.Test;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by mingguo.wu on 2016/9/21.
 */
public class Charpter01Test {

    @Test
    public void test01() {
        Counter counter = new Counter();
        int num = 0;
        while(true) {
            counter.increment();
            ++num;
            if(num % 1000 == 0) {
                System.out.println("now num is " + num);
            }
        }

    }

    //this逃逸,当内部类代码执行的时候，外部类对象的创建过程很有可能还没结束，
    //这个时候如果内部类访问外部类中的数据，很有可能得到还没有正确初始化的数据。
    //this逃逸就是说在构造函数返回之前其他线程就持有该对象的引用，
    //调用尚未构造完全的对象的方法可能引发错误。

    @ThreadSafe
    class MutableInteger {
        @GuardedBy("this")
        private int value;

        public synchronized int getValue() {
            return value;
        }

        public synchronized void setValue(int value) {
            this.value = value;
        }
    }

    @ThreadSafe
    final class Counter {
        @GuardedBy("this") private short value = 0;
        public synchronized short getValue() {
            return value;
        }
        public synchronized short increment() {
            if(value == Short.MAX_VALUE) {
                throw new IllegalStateException("count overflow, value is " + value);
            }
            return ++value;
        }
    }

    @ThreadSafe
    class MonitorVehicleTracker {
        @GuardedBy("this")
        private final Map<String, MutablePoint> locations;

        public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
            this.locations = deepCopy(locations);
        }

        public synchronized MutablePoint getLocation(String id) {
            MutablePoint loc = locations.get(id);
            return loc == null ? null : new MutablePoint(loc);
        }

        public synchronized void setLocation(String id, int x, int y) {
            MutablePoint loc = locations.get(id);
            if(loc == null) {
                throw new IllegalArgumentException("No such ID: " + id);
            }
            loc.x = x;
            loc.y = y;
        }

        private synchronized Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
            Map<String, MutablePoint> result = new HashMap<>();
            for(String id : m.keySet()) {
                result.put(id, new MutablePoint(m.get(id)));
            }
            return Collections.unmodifiableMap(result);
        }


        @Data
        class MutablePoint {
            private long x;
            private long y;
            MutablePoint(MutablePoint m) {
                this.x = m.getX();
                this.y = m.getY();
            }
        }
    }

    @ThreadSafe
    class DelegatingVehicleTracker {
        private final ConcurrentHashMap<String, Point> locations;
        private final Map<String, Point> unmodifiableMap;

        public DelegatingVehicleTracker(Map<String, Point> points) {
            locations = new ConcurrentHashMap<>(points);
            unmodifiableMap = Collections.unmodifiableMap(points);
        }

        /************************************
         * 以下这种方式返回不可修改却是实时的车辆变化
         * 如果仅仅需要车辆时刻快照，参照如下实现
         * @<code>
         * public Map<String, Point> getLocations(String id) {
         *     return Collections.unmodifiableMap(new HashMap<>(locations));
         * }
         * </code>
         **********************************/
        public Map<String, Point> getLocations() {
            return unmodifiableMap;
        }

        public Point getLocation(String id) {
            return locations.get(id);
        }

        public void setLocation(String id, int x, int y) {
            if(locations.replace(id, new Point(x, y)) == null) {
                throw new IllegalArgumentException("Invalid vehcle id:" + id);
            }
        }

        @Immutable @Data
        class Point {
            private final int x;
            private final int y;
            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }

    //VisualComponent使用CopyOnWriteArrayList来保存各个监控列表
    //其是一个线程安全的链表，特别适用于管理监听列表，每个列表
    //都是线程安全的，此外，各个状态之间不存在耦合关系，因此
    //该类将线程安全性委托给mouseListeners和keyListeners
    class VisualComponent {
        private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<>();
        private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();
        public void addKeyListener(KeyListener listener) {
            keyListeners.add(listener);
        }
        public void addMouseListener(MouseListener listener) {
            mouseListeners.add(listener);
        }
        public void removeKeyListener(KeyListener listener) {
            keyListeners.remove(listener);
        }
        public void removeMouseListener(MouseListener listener) {
            mouseListeners.remove(listener);
        }
    }

    //PublishingVehicleTracker将其线程安全性委托给底层的ConcurrentHashMap，只是
    //Map中的元素是线程安全且可变的Point，并非不可变的
    @ThreadSafe
    class PublishingVehicleTracker {

        private final Map<String, SafePoint> locations;
        private final Map<String, SafePoint> unmodifiableMap;

        public PublishingVehicleTracker(Map<String, SafePoint> locations) {
            this.locations = new ConcurrentHashMap<>(locations);
            this.unmodifiableMap = Collections.unmodifiableMap(locations);
        }

        public Map<String, SafePoint> getLocations() {
            return unmodifiableMap;
        }

        public SafePoint getLocation(String id) {
            return locations.get(id);
        }

        public void setLocation(String id, int x, int y) {
            if(!locations.containsKey(id)) {
                throw new IllegalArgumentException("Invalid vehicle id: " + id);
            }
            locations.get(id).set(x, y);
        }

        @ThreadSafe
        class SafePoint {
            @GuardedBy("this") private int x, y;
            private SafePoint(int[] a) {
                this(a[0], a[1]);
            }
            public SafePoint(int x, int y) {
                this.x = x;
                this.y = y;
            }
            public synchronized int[] get() {
                return new int[] {x, y};
            }
            public synchronized void set(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }



}
