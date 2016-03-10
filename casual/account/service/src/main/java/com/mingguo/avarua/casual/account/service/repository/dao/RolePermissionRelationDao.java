package com.mingguo.avarua.casual.account.service.repository.dao;

import com.mingguo.avarua.casual.account.model.RolePermissionRelation;

import java.util.List;
import java.util.Map;

/**
 * Created by mingguo.wu on 2015/10/8.
 */
public interface RolePermissionRelationDao {

    public int addRelation(RolePermissionRelation relation);

    public int updateRelation(RolePermissionRelation relation);

    public int deleteRelationByIds(List<Integer> ids);

    public int deleteRelationByRoleIdAndPermissionId(Integer roleId, Integer permissionId);

    public int getRelationCount();

    public List<RolePermissionRelation> getRelationList(Integer page, Integer pageSize);

    public int getRelationCountByRoleId(Integer roleId);

    public List<RolePermissionRelation> getRelationListByRoleId(Integer roleId, Integer page, Integer pageSize);

    public Map<Integer, Integer> getRelationMapByRoleIds(List<Integer> roleIds);

    public List<Integer> getPermissionIdsByRoleIds(List<Integer> roleIds);

    public int getPermission(Integer roleId, Integer permissionId);
}
