package com.mingguo.wu.account.test.repository.dao;

import com.alibaba.fastjson.JSON;
import com.mingguo.wu.account.model.Role;
import com.mingguo.avarua.casual.account.service.impl.RoleDao;
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
@ContextConfiguration({"classpath:/conf/account-rest-service.xml"})
public class RoleDaoTest {

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

        System.out.println("getRoleById(1):----->>>>>" + JSON.toJSONString(roleDao.getRoleById(1)));
        System.out.println("getRoleCount():------>>>>" + roleDao.getRoleCount());
        System.out.println("getRoleCountByFullRoleName('roleName'):-------->>>>>>"
                + roleDao.getRoleCountByFullRoleName("roleName"));
        System.out.println("getRoleCountByRoleName('roleName'):---------->>>>>>>"
                + roleDao.getRoleCountByRoleName("roleName"));
        System.out.println("getRoleList(1, 10)------>>>>>>\n" + JSON.toJSONString(roleDao.getRoleList(1, 10)));
        System.out.println("getRolesByIds(1,2):-------->>>>\n" + JSON.toJSONString(roleDao.getRolesByIds(Arrays.asList(1, 2))));
        System.out.println("getRolesByRoleName('roleName', 1, 10):----------->>>>>>>> \n"
                + JSON.toJSONString(roleDao.getRolesByRoleName("roleName", 1, 10)));

    }
}
