package com.mingguo.avarua.casual.account.service.impl;

import com.mingguo.avarua.casual.account.model.Permission;

import java.util.List;

/**
 * 权限数据访问接口
 * Created by wumingguo on 2015/9/23.
 */
public interface PermissionDao {

    /**
     * 新增权限
     * @param permission 权限对象
     * @return 新增权限对应的id，大于0表示成功，否则新增失败
     */
    public int addPermission(Permission permission);

    /**
     * 根据权限id列表删除对应的权限
     * @param ids 权限id列表
     * @return 受影响的行数
     */
    public int deletePermissionByIds(List<Integer> ids);

    /**
     * 根据id更新权限对象
     * @param permission 权限对象
     * @return 受影响的行数 1：成功，0：失败
     */
    public int updatePermission(Permission permission);

    /**
     * 获得所有的权限数目
     * @return 权限数目
     */
    public int getPermissionCount();

    /**
     * 模糊查询权限名对应的权限数目
     * @param permissionName 权限名称
     * @return
     */
    public int getPermissionCountByName(String permissionName);

    /**
     * 根据id查询对应的权限
     * @param id
     * @return
     */
    public Permission getPermissionById(Integer id);

    /**
     * 根据权限id列表查询到相应的权限列表
     * @param ids id列表
     * @return 权限对象列表
     */
    public List<Permission> getPermissionListByIds(List<Integer> ids);

    /**
     * 分页查询权限列表
     * @param page 页码
     * @param pageSize 页面大小
     * @return 所在页面的权限列表
     */
    public List<Permission> getPermissionList(Integer page, Integer pageSize);

    /**
     * 分页模糊查询权限名对应的权限列表
     * @param permissionName 权限名称
     * @param page 页码
     * @param pageSize 页面大小
     * @return 权限列表
     */
    public List<Permission> getPermissionListByName(String permissionName, Integer page, Integer pageSize);
}
