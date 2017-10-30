package com.mingguo.avarua.casual.account.test.common.mess.date;

import java.sql.Timestamp;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by wumingguo on 2017/10/29.
 */
public class TransferTest {


    public static void main(String[] args) {

        /**
         * 初始化方式对比
         */
        final Date date = new Date();
        final Timestamp timestamp = new Timestamp(date.getTime());
        final Calendar calendar = Calendar.getInstance();

        final Instant instant = Instant.now();
        final LocalDateTime localDateTime = LocalDateTime.now();
        final ZonedDateTime zonedDateTime = ZonedDateTime.now();

        /**
         * Instant的方式
         */
        //Date转Instant
        Instant dateInstant = date.toInstant();
        //Timestamp转Instant
        Instant timestampInstant = timestamp.toInstant();
        //Calendar转Instant
        Instant calendarInstant = calendar.toInstant();
        //LocalDateTime转Instant
        Instant localDateTimeInstant = localDateTime.toInstant(ZoneOffset.of(ZoneId.systemDefault().getId()));
        //ZonedDateTime转Instant
        Instant zonedDateTimeInstant = zonedDateTime.toInstant();


        /**
         * 转LocalDateTime
         */
        //Date转LocalDateTime
        LocalDateTime dateLocalDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        //Timestamp转LocalDateTime
        LocalDateTime timestampLocalDateTime = timestamp.toLocalDateTime();
        //Calendar转LocalDateTime
        LocalDateTime calendarLocalDateTime = LocalDateTime.ofInstant(calendar.toInstant(), ZoneOffset.systemDefault());
        //Instant转LocalDateTime
        LocalDateTime instantLocalDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        //ZonedDateTime转LocalDateTime
        LocalDateTime zonedDateTimeLocalDateTime = zonedDateTime.toLocalDateTime();


        /**
         * 转化date的方式
         */
        //Timestamp转Date
        Date timestampDate = new Date(timestamp.getTime());
        //Calendar转Date
        Date calendarDate = calendar.getTime();
        //Instant转Date
        Date instantDate = Date.from(instant);
        //LocalDateTime转Date
        Date localDateTimeDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        //ZonedDateTime转Date
        Date zonedDateTimeDate = Date.from(zonedDateTime.toInstant());


        /**
         * 转化Calendar的方式
         */
        //Date转Calendar
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);
        //Timestamp转Calendar
        Calendar timestampCalendar = Calendar.getInstance();
        timestampCalendar.setTimeInMillis(timestamp.getTime());
        //Instant转Calendar
        Calendar instantCalendar = GregorianCalendar.from(ZonedDateTime.ofInstant(instant, ZoneId.systemDefault()));
        //LocalDateTime转Calendar
        Calendar localDateTimeCalendar = GregorianCalendar.from(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()));
        //ZonedDateTime转Calendar
        Calendar zonedDateTimeInstantCalendar = GregorianCalendar.from(zonedDateTime);
    }

}
