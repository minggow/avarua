package com.mingguo.avarua.casual.account.test.dao;

import com.google.gson.Gson;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by mingguo.wu on 2016/4/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:app_conf/avarua-casual-account-datasource.xml",
        "classpath:app_conf/avarua-casual-account-service.xml",
        "classpath:mybatis/mybatis-config-account.xml"
})
public class BaseDaoTest {
    protected static final Gson GSON = new Gson();
    protected  /*static final */Logger LOGGER = LoggerFactory.getLogger(this.getClass());
}
