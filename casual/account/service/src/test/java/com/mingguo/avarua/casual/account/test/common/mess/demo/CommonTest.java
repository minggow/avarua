package com.mingguo.avarua.casual.account.test.common.mess.demo;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import lombok.*;
import org.junit.Test;

import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by mingguo.wu on 2015/9/16.
 */

public class CommonTest {

    private static final Gson GSON = new Gson();

    @Test
    public void test01() {
        LombokData lombokData = new LombokData(1, "name", "desc");
        System.out.println("toString:----->>> " + lombokData.toString());
        System.out.println("HashCode:----->>> " + lombokData.hashCode());

    }

    @Test
    public void test02() {
        //这里的数后面加“D”是表明它是Double类型，否则相除的话取整，无法正常使用
        double percent = 50.5D / 150D;
        //输出一下，确认你的小数无误
        System.out.println("小数：" + percent);
        //获取格式化对象
        NumberFormat nt = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(2);
        //最后格式化并输出
        System.out.println("百分数：" + nt.format(percent));
        NumberFormat formatter = new DecimalFormat("0.000");
        Double x = new Double(34.0 / 55.0);
        String xx = formatter.format(x);
        System.out.println(xx);

    }

    @Test
    public void test03() {
        double d = 0.0d;
        System.out.println("d ----->>>>> " + d);
        System.out.println("d == 0.0 ------>>>>> " + (d == 0));
        for (int i = 0; i < 5; ++i) {
            d += 1.0;
        }
        System.out.println("d ----->>>> " + d);
        System.out.println("d == 5.0 ------>>>>> " + (d == 5.0));
    }


    @Test
    public void test04() {
        char c1 = ' ';
        char c2 = ' ';
        char c3 = ' ';
        System.out.println((int) c1);
        System.out.println((int) c2);
        System.out.println((int) c3);

    }

    @Test
    public void test05() {
        List<String> strList = new ArrayList<>();
        strList.add("aa");
        strList.add("bb");
        strList.add("cc");
        StringBuilder sb = new StringBuilder();
        for (String s : strList) {
            sb.append(s + ",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        System.out.println(sb.toString());

    }

    @Test
    public void test06() {
        List<LombokData> lombokDataList = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            lombokDataList.add(new LombokData(i, i + "-name", i + "-desc"));
        }
        System.out.println("#####" + GSON.toJson(lombokDataList));
        Map<Integer, LombokData> lombokDataMap = Maps.uniqueIndex(lombokDataList, new Function<LombokData, Integer>() {
            @Override
            public Integer apply(LombokData lombokData) {
                return lombokData.getId();
            }
        });
        System.out.println("#####" + GSON.toJson(lombokDataMap));
        for (List lombokList : Lists.partition(lombokDataList, 2)) {
            System.out.println(">>>>" + GSON.toJson(lombokList));
        }
        List<LomData> lomDataList = Lists.transform(lombokDataList, new Function<LombokData, LomData>() {
            @Override
            public LomData apply(LombokData lombokData) {
                return new LomData(lombokData.getId(), lombokData.getName());
            }
        });
        System.out.println("#####" + GSON.toJson(lomDataList));
    }

    @Test
    public void test07() {
        Set<String> cidSet = new HashSet() {{
            add("111");
            add("222");
            add("333");
        }};
        List<String> cidList = new ArrayList<String>(cidSet);
        System.out.println("#### --->>> " + GSON.toJson(cidList));
    }

    @Test
    public void test08() {
        Map<String, Object> map = new HashMap<>();
        map.put("1", "111");
        map.put("2", null);
        System.out.println(map.get("2"));
//        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void test09() throws Exception {
        Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
        while (e.hasMoreElements()) {
            NetworkInterface ni = e.nextElement();
            System.out.println("displayname: " + ni.getDisplayName());
            System.out.println("name: " + ni.getName());
            System.out.println("MTU: " + ni.getMTU());
            System.out.println("Loopback: " + ni.isLoopback());
            System.out.println("Virtual: " + ni.isVirtual());
            System.out.println("Up: " + ni.isUp());
            System.out.println("PointToPoint: " + ni.isPointToPoint());
            byte[] mac = ni.getHardwareAddress();
            if (mac != null) {
                for (int i = 0; i < mac.length; i++) {
                    byte b = mac[i];
                    int intValue = 0;
                    if (b >= 0) {
                        intValue = b;
                    } else {
                        intValue = 256 + b;
                    }
                    System.out.print(Integer.toHexString(intValue));
                    if (i != mac.length - 1)
                        System.out.print("-");
                }
                System.out.println();
            } else {
                System.out.println("mac is null");
            }
            System.out.println("-----");
        }
    }

    @Test
    public void test10() {

        List<LombokData> lombokDataList = new ArrayList<LombokData>(){
            {
                add(new LombokData(1, "name1", "desc1"));
                add(new LombokData(2, "name2", "desc2"));
                add(new LombokData(3, "name3", "desc3"));
                add(new LombokData(4, "name4", "desc4"));
            }
        };


        Map<Integer, Integer> map = new HashMap<Integer, Integer>() {
            {
                put(2, 0);
                put(3, 0);
            }
        };
        System.out.println(GSON.toJson(lombokDataList));
        System.out.println("-----------------------------");
//        for(LombokData lombokData : lombokDataList) {
//            if(map.containsKey(lombokData.getId())) {
//                lombokDataList.remove(lombokData);
//            }
//        }
        Iterator<LombokData> it = lombokDataList.iterator();
        while(it.hasNext()) {
            LombokData lombokData = it.next();
            if(map.containsKey(lombokData.getId())) {
                it.remove();
            }
        }
        System.out.println(GSON.toJson(lombokDataList));
    }

    @Test
    public void test11() {
        List<Integer> dataList = new ArrayList<Integer>(){
            {
                add(5);
                add(4);
                add(1);
                add(2);
                add(3);
            }
        };

        Map<Integer, Map<String, Object>> dataMap = new HashMap<Integer, Map<String, Object>>();
        System.out.println(GSON.toJson(dataList));
        List<Object> result = new ArrayList<Object>();
        for(Integer uid : dataList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("uid", uid + 100000);
            map.put("data", "data");
            dataMap.put(uid, map);
            result.add(map);
        }

        System.out.println(GSON.toJson(result));
    }


    @Test
    public void test12() {

        String s = "首次笑嘻嘻xxx";

        System.out.println(s.substring(2));

        Map<String, Object> map = Maps.newHashMap();
        map.put("key1", 11111111);
        map.put("key2", "asdfasdfasd");
        System.out.println(GSON.toJson(map));
    }

    @Data
    @RequiredArgsConstructor
    @AllArgsConstructor
    class LombokData {
        private Integer id;
        @NonNull
        private String name;
        @NonNull
        private String desc;
    }

    @Data
    @RequiredArgsConstructor
    @AllArgsConstructor
    class LomData {
        private Integer id;
        @NonNull
        private String name;
    }


}

