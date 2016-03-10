package com.mingguo.avarua.casual.account.test.dao;

import com.alibaba.fastjson.JSON;
import com.mingguo.avarua.casual.account.model.Role;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by wumingguo on 2015/9/20.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({
//        "classpath*:app_conf/*.xml",
//        "classpath*:mybatis/mybatis-config-account.xml",
//})
public class RoleDaoTest {

//    @Autowired
//    private RoleDao roleDao;
@Test(expected = Exception.class)
public void testCheck() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
            new String[]{
        "classpath*:app_conf/*.xml",
        "classpath*:mybatis/mybatis-config-account.xml"
            });
    Object bean = applicationContext.getBean("sqlSessionTemplate");
    System.out.println(bean);
}

//    @Test
//    public void testAddRole() {
//
//        //set values
//        Role role = new Role();
//        role.setCreateTime(new Date());
//        role.setRoleName("roleName2");
//        role.setDescription("roleDescription");
//        role.setPermissionCount(0);
//
//        System.out.println("addRole:------>>>>> " + roleDao.addRole(role));
//    }
//
//    @Test
//    public void testGetRoles() {
//
//        System.out.println("getRoleById(1):----->>>>>" + JSON.toJSONString(roleDao.getRoleById(1)));
//        System.out.println("getRoleCount():------>>>>" + roleDao.getRoleCount());
//        System.out.println("getRoleCountByFullRoleName('roleName'):-------->>>>>>"
//                + roleDao.getRoleCountByFullRoleName("roleName"));
//        System.out.println("getRoleCountByRoleName('roleName'):---------->>>>>>>"
//                + roleDao.getRoleCountByRoleName("roleName"));
//        System.out.println("getRoleList(1, 10)------>>>>>>\n" + JSON.toJSONString(roleDao.getRoleList(1, 10)));
//        System.out.println("getRolesByIds(1,2):-------->>>>\n" + JSON.toJSONString(roleDao.getRolesByIds(Arrays.asList(1, 2))));
//        System.out.println("getRolesByRoleName('roleName', 1, 10):----------->>>>>>>> \n"
//                + JSON.toJSONString(roleDao.getRolesByRoleName("roleName", 1, 10)));
//
//    }
}
