package com.mingguo.avarua.casual.account.service.repository;

import com.mingguo.avarua.casual.account.model.User;
import java.util.List;

/**
 * 用户相关仓库相关访问接口
 * Created by mingguo.wu on 2015/9/15.
 */
public interface UserRepository {

    /**
     * 新增用户，需要填写用户名和密码，否则直接失败退出
     * @param user 索要新增的用户对象
     * @return 新增用户对应的id，大于0成功，否则失败
     */
    public int addUser(User user);

    /**
     * 更新用户信息，密码不能为空
     * @param user 所要更新的用户对象
     * @return 受影响的行数 1：成功，0：失败
     */
    public int updateUser(User user);

    /**
     * 根据用户id列表删除对应的用户
     * @param ids 用户id列表
     * @return 受影响的行数
     */
    public int deleteUser(List<Integer> ids) ;

    /**
     * 根据用户id查询用户
     * @param userId 用户id
     * @return 简单用户对象
     */
    public User getUserById(Integer userId);

    /**
     * 根据用户id查询带角色的用户
     * @param userId 用户id
     * @return 带角色的用户
     */
    public User getUserWithRoleByUserId(Integer userId);

    /**
     * 查询用户是否具备每个角色
     * @param userId 用户id
     * @param roleId 角色id
     * @return 是否具备某个角色
     */
    public boolean isUserOwnRole(Integer userId, Integer roleId);

    /**
     * 根据用户id查询带有权限列表的用户对象
     * @param userId 用户id
     * @return 带权限列表的用户对象
     */
    public User getUserWithPermissionByUserId(Integer userId);

}
