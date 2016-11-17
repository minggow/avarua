package com.mingguo.avarua.casual.account.test.common.mess.demo;

import com.google.gson.Gson;
import org.junit.Test;

/**
 * Created by mingguo.wu on 2016/11/14.
 */
public class RegexpTest {

    @Test
    public void test01() {
        String codeList = "\">\n<img src=\"#\"\n\n onerror=\"alert(1)\"\n";

        //deal result is img src oneerror alert(1)

        codeList = codeList.toUpperCase().replaceAll("[^0-9A-Z ]","");
        String[] codeArr = codeList.trim().split("\\s+");

        System.out.println(new Gson().toJson(codeArr));
    }
}
