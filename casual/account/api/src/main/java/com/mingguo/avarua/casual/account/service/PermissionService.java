package com.mingguo.avarua.casual.account.service;

import com.mingguo.avarua.casual.account.model.Permission;

/**
 * Permission服务API接口
 * Created by mingguo.wu on 2015/9/15.
 */
public interface PermissionService {
    /**
     * 增加权限接口方法
     * @param permission 所要增加的权限对象
     * @return 0:增加失败; 大于0:新增权限对应的id
     */
    public int addPermission(Permission permission);

    /**
     * 删除权限接口方法
     * @param id 所要删除的权限对应的id
     * @return false:删除失败; true:删除成功
     */
    public boolean deletePermission(Integer id);

    /**
     * 更新权限接口方法
     * @param permission 所要更新的权限对象
     * @return false:更新失败; true:更新成功
     */
    public boolean updatePermission(Permission permission);

    /**
     * 根据权限id查询对应的权限详情
     * @param id 所要查询的权限对应的id
     * @return null:查询失败; id对应的权限对象
     */
    public Permission getPermissionById(Integer id);
}
