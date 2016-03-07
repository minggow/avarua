package com.mingguo.avarua.casual.account.service;

import com.mingguo.avarua.casual.account.model.Role;

/**
 * Role服务API接口
 * Created by mingguo.wu on 2015/9/15.
 */
public interface RoleService {
    /**
     * 增加角色接口方法
     * @param role 所要增加角色对象
     * @return 0:增加失败; 大于0:新增角色对应的id
     */
    public int addRole(Role role);

    /**
     * 删除角色接口方法
     * @param id 所要删除角色对应的id
     * @return false:删除失败; true:删除成功
     */
    public boolean deleteRole(Integer id);

    /**
     * 更新角色接口方法
     * @param role 所要更新的角色对象
     * @return false:更新失败; true:更新失败成功
     */
    public boolean updateRole(Role role);

    /**
     * 根据角色id查询角色详情
     * @param id 所要查询的角色对应的id
     * @return null:没有对应角色; id对应角色对象
     */
    public Role getRoleById(Integer id);
}
