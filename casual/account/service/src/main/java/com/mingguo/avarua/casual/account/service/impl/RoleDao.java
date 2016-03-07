package com.mingguo.avarua.casual.account.service.impl;

import com.mingguo.avarua.casual.account.model.Role;

import java.util.List;

/**
 * 角色数据访问对象接口
 * Created by mingguo.wu on 2015/9/18.
 */
public interface RoleDao {

    /**
     * 新增角色对象
     * @param role 角色
     * @return 新增角色对应的id，大于0成功，否则失败
     */
    public int addRole(Role role);

    /**
     * 根据角色id列表删除对应的角色
     * @param ids 用户角色id列表
     * @return 受影响的行数
     */
    public int deleteRolesByIds(List<Integer> ids);

    /**
     * 根据角色id更新角色信息
     * @param role 角色对象
     * @return 受影响的行数 1：成功，0：失败
     */
    public int updateRole(Role role);

    /**
     * 查询所有的角色数目
     * @return 所有的角色数目
     */
    public Integer getRoleCount();

    /**
     * 根据角色id查询对应的角色
     * @param id 角色id
     * @return 角色对象
     */
    public Role getRoleById(Integer id);

    /**
     * 根据角色id列表查询对应的角色列表
     * @param ids 角色id列表
     * @return 角色列表
     */
    public List<Role> getRolesByIds(List<Integer> ids);

    /**
     * 无条件分页查询
     * @param page 页码
     * @param pageSize 页面大小
     * @return 查询到的角色列表
     */
    public List<Role> getRoleList(Integer page, Integer pageSize);

    /**
     * 根据角色名称模糊查询角色对应数目
     * @param roleName 角色名称
     * @return 模糊查询到的角色对应数目
     */
    public Integer getRoleCountByRoleName(String roleName);

    /**
     * 根据角色名称查询对应的角色数目，精确查询
     * @param roleName
     * @return
     */
    public Integer getRoleCountByFullRoleName(String roleName);

    /**
     * 根据角色名称分页查询对应的角色列表
     * @param roleName 角色名称
     * @param page 页码
     * @param pageSize 页面大小
     * @return 角色列表
     */
    public List<Role> getRolesByRoleName(String roleName, Integer page, Integer pageSize);

}
