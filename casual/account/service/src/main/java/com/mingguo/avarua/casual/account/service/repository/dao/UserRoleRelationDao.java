package com.mingguo.avarua.casual.account.service.repository.dao;

import com.mingguo.avarua.casual.account.model.UserRoleRelation;
import java.util.List;

/**
 * 用户角色访问对象接口
 * Created by wumingguo on 2015/9/21.
 */
public interface UserRoleRelationDao {

    /**
     * 新增用户角色对应关系
     * @param userRoleRelation 用户角色对应关系
     * @return 新增用户角色对应关系对象id，大于0成功，否则失败
     */
    public int addRelation(UserRoleRelation userRoleRelation);

    /**
     * 更新用户角色对应关系
     * @param userRoleRelation 用户角色对应关系对象
     * @return 受影响的行数
     */
    public int updateRelation(UserRoleRelation userRoleRelation);

    /**
     * 根据id列表删除用户角色对应关系
     * @param ids 用户角色列表
     * @return 受影响的行数
     */
    public int deleteRelationByIds(List<Integer> ids);

    /**
     * 根据id查询对应的用户角色关系对象
     * @param id 用户角色对应关系对象id
     * @return 用户角色对应关系对象
     */
    public UserRoleRelation getRelationById(Integer id);

    /**
     * 根据用户id和角色id删除对应的用户角色关系
     * @param userId 用户id
     * @param roleId 角色id
     * @return 受影响的行数
     */
    public int deleteRelationByUserRole(Integer userId, Integer roleId);

    /**
     * 查询角色id对应的用户角色关系数目
     * @param roleId 用户角色id
     * @return 用户角色关系数目
     */
    public int getRelationCountByRoleId(Integer roleId);

    /**
     * 分页根据角色id查询对应的用户角色关系对象列表
     * @param roleId 角色id
     * @param page 页码
     * @param pageSize 页面大小
     * @return 用户角色关系对象列表
     */
    public List<UserRoleRelation> getRelationsByRoleId(Integer roleId, Integer page, Integer pageSize);

    /**
     * 根据用户id列表查询对应的用户角色关系对象列表
     * @param userIds 用户id列表
     * @return 用户角色关系对象列表
     */
    public List<UserRoleRelation> getRelationsByUserIds(List<Integer> userIds);

    /**
     * 根据用户id查询对应的用户角色关系对象列表
     * @param userId 用户id
     * @return 用户角色关系对象列表
     */
    public List<UserRoleRelation> getRelationsByUserId(Integer userId);

    /**
     * 根据用户id和角色id查询用户角色对应关系对象
     * @param userId 用户id
     * @param roleId 角色id
     * @return 用户角色关系对象
     */
    public UserRoleRelation getRelationByUserIdAndRoleId(int userId, int roleId);
}
