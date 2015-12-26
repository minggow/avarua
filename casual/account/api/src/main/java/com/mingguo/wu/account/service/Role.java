package com.mingguo.wu.account.service;

/**
 * Role服务API接口
 * Created by mingguo.wu on 2015/9/15.
 */
public interface Role {
    /**
     * 增加角色接口方法
     * @param role 所要增加角色对象
     * @return 0:增加失败; 1:增加成功
     */
    public int addRole(Role role);

    /**
     * 删除角色接口方法
     * @param id 所要删除角色对应的id
     * @return 0:增加失败; 1:增加成功
     */
    public int deleteRole(Integer id);

    /**
     * 更新角色接口方法
     * @param role 所要更新的角色对象
     * @return 0:增加失败; 1:增加成功
     */
    public int updateRole(Role role);

    /**
     * 根据角色id查询角色详情
     * @param id 所要查询的角色对应的id
     * @return 0:增加失败; 1:增加成功
     */
    public Role getRoleById(Integer id);
}
