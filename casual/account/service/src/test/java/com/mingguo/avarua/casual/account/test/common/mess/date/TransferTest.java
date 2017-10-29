package com.mingguo.avarua.casual.account.test.common.mess.date;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wumingguo on 2017/10/29.
 */
public class TransferTest {


    @Test
    public void test01() {
        final Date date = new Date();
        final Timestamp timestamp = new Timestamp(date.getTime());
        final Calendar calendar = Calendar.getInstance();

        final Instant instant = Instant.now();
        final LocalDateTime localDateTime = LocalDateTime.now();
        final ZonedDateTime zonedDateTime = ZonedDateTime.now();
    }
}
