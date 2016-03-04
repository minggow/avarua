package com.mingguo.avarua.casual.account.service;

import com.mingguo.wu.account.model.User;

/**
 * User服务API接口
 * Created by mingguo.wu on 2015/9/15.
 */
public interface UserService {
    /**
     * 增加用户接口
     * @param user 用户对象
     * @return 0:增加失败; 1:增加成功
     */
    public int addUser(User user);

    /**
     * 删除用户接口
     * @param id 所要删除的用户id
     * @return 0:增加失败; 1:增加成功
     */
    public int deleteUser(Integer id);

    /**
     * 更新用户信息接口
     * @param user 所要更新的用户对象
     * @return 0:增加失败; 1:增加成功
     */
    public int updateUser(User user);

    /**
     * 根据用户id查询用户详情
     * @param id 用户的id
     * @return id对应用户对象
     */
    public User getUserById(Integer id);


}
