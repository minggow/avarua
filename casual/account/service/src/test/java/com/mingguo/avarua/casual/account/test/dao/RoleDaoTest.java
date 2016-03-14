package com.mingguo.avarua.casual.account.test.dao;

import com.google.gson.Gson;
import com.mingguo.avarua.casual.account.model.Role;
import com.mingguo.avarua.casual.account.service.repository.dao.RoleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
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
        "classpath:mybatis/mybatis-config-account.xml",
})
public class RoleDaoTest {

    private static final Gson GSON = new Gson();
    @Autowired
    private RoleDao roleDao;

    @Test
    public void testAddRole() {

        //set values
        Role role = new Role();
        role.setCreateTime(new Date());
        role.setRoleName("roleName2");
        role.setDescription("roleDescription");
        role.setPermissionCount(0);

        System.out.println("addRole:------>>>>> " + roleDao.addRole(role));
    }

    @Test
    public void testGetRoles() {

        System.out.println("getRoleById(1):----->>>>>" + GSON.toJson(roleDao.getRoleById(1)));
        System.out.println("getRoleCount():------>>>>" + roleDao.getRoleCount());
        System.out.println("getRoleCountByFullRoleName('roleName'):-------->>>>>>"
                + roleDao.getRoleCountByFullRoleName("roleName"));
        System.out.println("getRoleCountByRoleName('roleName'):---------->>>>>>>"
                + roleDao.getRoleCountByRoleName("roleName"));
        System.out.println("getRoleList(1, 10)------>>>>>>\n" + GSON.toJson(roleDao.getRoleList(1, 10)));
        System.out.println("getRolesByIds(1,2):-------->>>>\n" + GSON.toJson(roleDao.getRolesByIds(Arrays.asList(1, 2))));
        System.out.println("getRolesByRoleName('roleName', 1, 10):----------->>>>>>>> \n"
                + GSON.toJson(roleDao.getRolesByRoleName("roleName", 1, 10)));

    }
}
