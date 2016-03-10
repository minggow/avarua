package com.mingguo.avarua.casual.account.test.service;

/**
 * Created by mingguo.wu on 2015/11/18.
 */
public class MainTest {
    public static void main(String[] args) {
        if(args == null || args.length == 0) {
            System.out.println("请正确输入参数！！！");
            System.exit(0);
        }
        int day;
        day = Integer.parseInt(args[0]);
        System.out.println("The day is :  " + day + "!!!");
    }
}
