package com.mingguo.avarua.casual.account.test.common.mess.concurrent;

import org.junit.Test;

/**
 * Created by mingguo.wu on 2016/11/15.
 */
public class Charpter001Test {

    @Test
    public void test121() {
        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void test122() throws InterruptedException{
        MyThread1 myThread = new MyThread1();
        System.out.println("begin == " + myThread.isAlive());
        myThread.start();
        Thread.sleep(1000);
        System.out.println("end == " + myThread.isAlive());
    }

    @Test
    public void test123() {
        CountOperate countOperate = new CountOperate();
        Thread t1 = new Thread(countOperate);
        System.out.println("main begin t1 isAlive=" + t1.isAlive());
        t1.setName("A");
        t1.start();;
        System.out.println("main end t1 isAlive=" + t1.isAlive());
    }

    class MyThread2 extends Thread {

    }

    class CountOperate extends Thread {
        public CountOperate() {
            System.out.println("CountOpearate---begin");
            System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
            System.out.println("Thread.currentThread.isAlive()=" + Thread.currentThread().isAlive());
            System.out.println("this.getName()=" + this.getName());
            System.out.println("this.isAlive()=" + this.isAlive());
            System.out.println("CountOpearate---end");
        }
        @Override
        public void run() {
            System.out.println("run---begin");
            System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
            System.out.println("Thread.currentThread.isAlive()=" + Thread.currentThread().isAlive());
            System.out.println("this.getName()=" + this.getName());
            System.out.println("this.isAlive()=" + this.isAlive());
            System.out.println("run---end");
        }
    }

    class MyThread1 extends Thread {
        @Override
        public void run() {
            System.out.println("run=" + this.isAlive());
        }
    }
}
