package com.mingguo.avarua.casual.account.test.common.mess.demo;

import lombok.Cleanup;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mingguo.wu on 2015/10/12.
 */
public class CommonExampleTest {

    @Test
    public void test01() throws IOException {
        //lombok @Cleanup before
        InputStream in = new FileInputStream(/*args[0]*/"");
        try {
            OutputStream out = new FileOutputStream(/*args[1]*/"");
            try {
                byte[] b = new byte[10000];
                while (true) {
                    int r = in.read(b);
                    if (r == -1) break;
                    out.write(b, 0, r);
                }
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    @Test
    public void test02(/*String[] args*/) throws IOException {
        //lombok @Cleanup after
        @Cleanup InputStream in = new FileInputStream(/*args[0]*/"");
        @Cleanup OutputStream out = new FileOutputStream(/*args[1]*/"");
        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1) break;
            out.write(b, 0, r);
        }
    }

    @Test
    public void test03() {
        for(String s : getStringList()) {
            System.out.println(s);
        }
    }

    @Test
    public void test04() {
        System.out.println(CorpEnum.CLOUD.hashCode());
        System.out.println(CorpEnum.NORMAL.hashCode());
    }

    @Test
    public void test05() {
        int day = 2;
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, day * -1);
        Date updateDate = now.getTime();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateDate));

    }

    protected List<String> getStringList() {
        return new ArrayList<>();
    }

    enum CorpEnum implements CodeEnum {
        NORMAL(1),
        CLOUD(0);

        private final int code;

        private CorpEnum(int code) {
            this.code = code;
        };

        @Override
        public int getCode() {
            return 0;
        }
    }

    interface CodeEnum {
        public int getCode();
    }


}
