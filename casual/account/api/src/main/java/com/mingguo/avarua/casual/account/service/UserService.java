package com.mingguo.avarua.casual.account.service;

import com.mingguo.avarua.casual.account.model.User;

/**
 * User服务API接口
 * Created by mingguo.wu on 2015/9/15.
 */
public interface UserService {
    /**
     * 增加用户接口
     * @param user 用户对象
     * @return 0:增加失败; 大于0:新增用户对应的id
     */
    public int addUser(User user);

    /**
     * 删除用户接口
     * @param id 所要删除的用户id
     * @return false:删除失败; true:删除成功
     */
    public boolean deleteUser(Integer id);

    /**
     * 更新用户信息接口
     * @param user 所要更新的用户对象
     * @return false:更新失败; true:更新成功
     */
    public boolean updateUser(User user);

    /**
     * 根据用户id查询用户详情
     * @param id 用户的id
     * @return id对应用户对象或者null
     */
    public User getUserById(Integer id);


}
