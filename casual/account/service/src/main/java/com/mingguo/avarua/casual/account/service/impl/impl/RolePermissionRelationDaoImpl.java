package com.mingguo.avarua.casual.account.service.impl.impl;

import com.mingguo.avarua.casual.account.model.RolePermissionRelation;
import com.mingguo.avarua.casual.account.service.impl.RolePermissionRelationDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by mingguo.wu on 2015/10/8.
 */
@Component("rolePermissionRelationDao")
public class RolePermissionRelationDaoImpl implements RolePermissionRelationDao{

    private final String NAMESPACE = "com.mingguo.avarua.casual.account.service.impl.RolePermissionRelationDao.";

    @Autowired
    private SqlSessionTemplate sqlSessionTemplateAccount;

    @Override
    public int addRelation(RolePermissionRelation relation) {
        sqlSessionTemplateAccount.insert(NAMESPACE + "addRolePermissionRelation", relation);
        return relation.getId() == null ? 0 : relation.getId();
    }

    @Override
    public int updateRelation(RolePermissionRelation relation) {
        return 0;
    }

    @Override
    public int deleteRelationByIds(List<Integer> ids) {
        return 0;
    }

    @Override
    public int deleteRelationByRoleIdAndPermissionId(Integer roleId, Integer permissionId) {
        return 0;
    }

    @Override
    public int getRelationCount() {
        return 0;
    }

    @Override
    public List<RolePermissionRelation> getRelationList(Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public int getRelationCountByRoleId(Integer roleId) {
        return 0;
    }

    @Override
    public List<RolePermissionRelation> getRelationListByRoleId(Integer roleId, Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public Map<Integer, Integer> getRelationMapByRoleIds(List<Integer> roleIds) {
        return null;
    }

    @Override
    public List<Integer> getPermissionIdsByRoleIds(List<Integer> roleIds) {
        return null;
    }

    @Override
    public int getPermission(Integer roleId, Integer permissionId) {
        return 0;
    }


}
