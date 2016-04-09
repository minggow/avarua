package com.mingguo.avarua.casual.account.test.dao;

import com.mingguo.avarua.casual.account.model.User;
import com.mingguo.avarua.casual.account.service.repository.dao.UserDao;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by mingguo.wu on 2015/9/16.
 */
public class UserDaoTest extends BaseDaoTest{

    private static final String LOGIN_NAME1 = "wumingguo1";
    private static final String LOGIN_NAME2 = "wumingguo2";
    private User user1;
    private User user2;

    @Autowired
    private UserDao userDao;

    @BeforeClass
    public void setUp() {
        tearDown();
        //init user1 & user2
        user1.setLoginName(LOGIN_NAME1);
        user1.setDescription("desc " + LOGIN_NAME1);
        user1.setDisplayName(LOGIN_NAME1);
        user1.setLoginCount(1);
        user1.setPassword(LOGIN_NAME1 + " password");
        user1.setLastLoginTime(new Date());

        user2.setLoginName(LOGIN_NAME2);
        user2.setDescription("desc " + LOGIN_NAME2);
        user2.setLoginCount(2);
        user2.setPassword(LOGIN_NAME2 + " password");
        user2.setLastLoginTime(new Date());
    }

    @AfterClass
    public void tearDown() {
        //delete ${LOGIN_NAME1}
        User user1 = userDao.getUserByLoginName(LOGIN_NAME1);
        if(user1 != null) { //start delete
            int id = user1.getId();
            Assert.assertEquals(1, userDao.deleteUsersByIds(Arrays.asList(id)));
        }
        //delete ${LOGIN_NAME2}
        User user2 = userDao.getUserByLoginName(LOGIN_NAME2);
        if(user2 != null) { //start delete
            int id = user2.getId();
            Assert.assertEquals(1, userDao.deleteUsersByIds(Arrays.asList(id)));
        }

    }

    @Test
    public void testAddUser() {
        //add user1
        Assert.assertEquals(true, userDao.addUser(user1) > 0);
        //add user2
        Assert.assertEquals(true, userDao.addUser(user2) > 0);
    }

    @Test
    public void testGetUsers() {
        //根据loginName查询用户
        User user1 = userDao.getUserByLoginName(LOGIN_NAME1);
        Assert.assertNotEquals(null, user1);

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
