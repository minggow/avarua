package com.mingguo.wu.account.repository.dao;

import com.mingguo.wu.account.model.User;
import java.util.List;

/**
 * user数据访问接口
 * Created by mingguo.wu on 2015/9/16.
 */
public interface UserDao {

    /**
     * 新增有个用户
     * @param user 用户对象
     * @return 新增用户的id，大于0则成功，否则失败
     */
    public int addUser(User user);

    /**
     * 根据id更新用户的相关信息
     * @param user 所要更新的用户对象
     * @return 受影响的行数，1：成功，0：失败
     */
    public int updateUser(User user);

    /**
     * 根据用户id列表删除用户对象
     * @param idList 用户id列表
     * @return 受影响的行数
     */
    public int deleteUsersByIds(List<Integer> idList);

    /**
     * 根据用户id获得用户相信信息
     * @param id 用户对应的id
     * @return 用户对象
     */
    public User getUserById(Integer id);

    /**
     * 根据用户登录名精确匹配用户，查出用户对象
     * @param loginName 用户登录名
     * @return 该用户对象
     */
    public User getUserByLoginName(String loginName);

    /**
     * 根据用户id列表查询用户对象列表
     * @param ids 用户id列表
     * @return 用户对象列表
     */
    public List<User> getUsersByIds(List<Integer> ids);

    /**
     * 根据用户名模糊匹配登录名，查询所对应的数目
     * @param userName 用户名
     * @return 查询到的数目
     */
    public int getCountByUserName(String userName);

    /**
     * 分页查询用户名模糊匹配的用户
     * @param userName 用户名
     * @param page 所在页码
     * @param pageSize 页面大小
     * @return 用户列表
     */
    public List<User> getUsersByUserName(String userName, Integer page, Integer pageSize);

    /**
     * 查询所有的用户数目
     * @return 用户数目
     */
    public int getUserCount();

    /**
     * 不带条件分页查询用户
     * @param page 页码
     * @param pageSize 页面大小
     * @return 该页对应的用户列表
     */
    public List<User> getUserList(Integer page, Integer pageSize);

}
