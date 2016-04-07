package com.mingguo.avarua.casual.account.test.dao;

import com.mingguo.avarua.casual.account.model.User;
import com.mingguo.avarua.casual.account.service.repository.dao.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by mingguo.wu on 2015/9/16.
 */
public class UserDaoTest extends BaseDaoTest{

    private static final String LOGIN_NAME1 = "wumingguo";
    private static final String LOGIN_NAME2 = "wumingguo2";

    @Autowired
    private UserDao userDao;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testAddUser() {
        User user = new User();
        //set value
        user.setLoginName("wumingguo2");
        user.setDescription("user wumingguo");
        user.setDisplayName("wumingguo");
        user.setLoginCount(0);
        user.setPassword("password");
        user.setLastLoginTime(new Date());

        System.out.println("addUser result:--->>> " + userDao.addUser(user));
    }

    @Test
    public void testGetUsers() {
        //根据loginName查询用户
        User user1 = userDao.getUserByLoginName("wumingguo");
        System.out.println("getUserByLoginName('wumingguo')------->>>>>>>\n" + GSON.toJson(user1));

        //根据id查询用户
        User user2 = userDao.getUserById(1);
        System.out.println("getUserById(1)------------>>>>>>>\n" + GSON.toJson(user2));

        //查询登录名数目，模糊查询
        int size1 = userDao.getCountByUserName("mingguo");
        System.out.println("getCountByUserName('mingguo')------>>>>>\n" + GSON.toJson(size1));

        //查询所有的用户总数
        int size2 = userDao.getUserCount();
        System.out.println("getUserCount()------>>>> " + size2);

        //分页查询用户列表
        List<User> userList1 = userDao.getUserList(1, 10);
        System.out.println("getUserList(1, 10)------>>>>> \n" + GSON.toJson(userList1));

        //根据id列表查询用户
        List<User> userList2 = userDao.getUsersByIds(new ArrayList<Integer>(){
            {
                add(1);
                add(2);
            }
        });
        System.out.println("getUsersByIds(1,2)------>>>>> \n" + GSON.toJson(userList2));

        List<User> userList3 = userDao.getUsersByUserName("mingguo",1, 10);
        System.out.println("getUsersByUserName('mingguo',1,10)------>>>>\n" + GSON.toJson(userList3));

    }

    @Test
    public void testUpdateUser() {

        User user = new User();
        //set value
        user.setLoginName("wumingguo");
        user.setDescription("user wumingguo");
        user.setDisplayName("wumingguo");
        user.setLoginCount(0);
        user.setPassword("password update");
        user.setLastLoginTime(new Date());
        user.setId(1);

        System.out.println("updateUser result:--->>> " + userDao.updateUser(user));

    }

    @Test
    public void testDeleteUser() {

        int result = userDao.deleteUsersByIds(Collections.singletonList(1));
        System.out.println("deleteUsersByIds(1):----->>>> " + result);
    }

}
