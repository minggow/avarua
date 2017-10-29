package com.mingguo.avarua.casual.account.test.common.mess.date;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wumingguo on 2017/10/29.
 */
public class DateTest {
    public static void main(String[] args) {



//        1、Date和Calendar的year和month的参数不统一。
        Date date =new Date(2012,1,1);//输出ThuFeb0100:00:00CST3912
//year 是1900 + year，而month月份从0开始（一月对应的时0）
        Calendar calendar=Calendar.getInstance();
        calendar.set(2013,8,2);
        calendar.set(2013,Calendar.AUGUST,2);
//Calendar年份的传值不需要减去1900（当然月份的定义和Date还是一样）
//Calendar相关的API是IBM捐出去的，所以才导致不一致。



//        2、计算两个日期之间的天数
        Calendar birth = Calendar.getInstance();
        birth.set(1975, Calendar.MAY, 26);
        Calendar now = Calendar.getInstance();
        System.out.println(daysBetween(birth, now));
        System.out.println(daysBetween(birth, now)); // 显示 0？

//        如果连续计算两个Date实例的话，第二次会取得0，因为Calendar状态是可变的，考虑到重复计算的场合，最好复制一个新的Calendar
//        3、date 和calendar 都是值可变得，标准的不一致，设计的不统一
//        导致date和time api非常难用
    }


    private static long daysBetween(Calendar begin, Calendar end) {
        long daysBetween = 0;
        while(begin.before(end)) {
            begin.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }
}
