package com.mingguo.avarua.casual.account.test.common.mess.demo;

import org.junit.Test;

/**
 * Created by wumingguo on 2016/12/15.
 */
public class CasualTest {

    @Test
    public void test() {
        int x = 1;
        int y = x;

        x = 3;

        System.out.println("x = " + x + ", y = " + y);
    }

    @Test
    public void test01() {
//        double a = 0.1d;
//        double b = 0.2d;
//        double c = 0.4d;
//        double d = 0.6d;

        float a = 0.1f;
        float b = 0.2f;
        float c = 0.4f;
        float d = 0.6f;


        System.out.println(a+b);
        System.out.println(c+d);

        System.out.println(a+b == 0.3d);
        System.out.println(c+d == 1.0d);
    }

    @Test
    public void test02() {
        Integer a = new Integer(100);
        Integer b = new Integer(1000);
        Integer c = new Integer(10000);

        Integer i = new Integer(100);
        Integer j = 1000;

        System.out.println(a == i);
        System.out.println(b == j);
        System.out.println(c == 10000);
    }

}
