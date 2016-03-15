package com.mingguo.avarua.casual.account.test.dao;

import com.google.gson.Gson;
import com.mingguo.avarua.casual.account.model.Role;
import com.mingguo.avarua.casual.account.service.repository.dao.RoleDao;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by wumingguo on 2015/9/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:app_conf/avarua-casual-account-datasource.xml",
        "classpath:app_conf/avarua-casual-account-service.xml",
        "classpath:mybatis/mybatis-config-account.xml"
})
public class RoleDaoTest {

    private static final Gson GSON = new Gson();
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleDaoTest.class);

    @Autowired
    private RoleDao roleDao;
    private Role role;

    @Before
    public void init() {
        role = new Role();
        role.setCreateTime(new Date());
        role.setRoleName("roleName");
        role.setDescription("roleDescription");
        role.setPermissionCount(0);
    }

    @Test
    public void testAddRole() {
        int result = roleDao.addRole(role);
        Assert.assertNotEquals(result, 0);
        LOGGER.info("addRole, the add id is {}", result);

        LOGGER.info("getRoleById(1):--->>> {}", GSON.toJson(roleDao.getRoleById(1)));
        LOGGER.info("getRoleCount():------>>>> {}", roleDao.getRoleCount());
        LOGGER.info("getRoleCountByFullRoleName('{}'):-------->>>>>> {}"
                , role.getRoleName(), roleDao.getRoleCountByFullRoleName("roleName"));
        LOGGER.info("getRoleCountByRoleName('{}'):---------->>>>>>> {}"
                , role.getRoleName(), roleDao.getRoleCountByRoleName("roleName"));
        LOGGER.info("getRoleList(1,3)------>>>>>> {}", GSON.toJson(roleDao.getRoleList(1, 3)));
        LOGGER.info("getRolesByIds({}):-------->>>>{}", GSON.toJson(Arrays.asList(role.getId())),
                GSON.toJson(roleDao.getRolesByIds(Arrays.asList(role.getId()))));
    }

    @After
    public void delete() {
        LOGGER.info("after test, now delete the add role, the id is {}", role.getId());
        int result = roleDao.deleteRolesByIds(Arrays.asList(role.getId()));
        Assert.assertNotEquals(0, result);
        LOGGER.info("delete done, the result is {}", result);
    }
}
