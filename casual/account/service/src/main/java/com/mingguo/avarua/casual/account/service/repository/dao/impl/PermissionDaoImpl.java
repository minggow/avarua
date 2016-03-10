package com.mingguo.avarua.casual.account.service.repository.dao.impl;

import com.mingguo.avarua.casual.account.model.Permission;
import com.mingguo.avarua.casual.account.service.repository.dao.PermissionDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mingguo.wu on 2015/10/8.
 */
@Component("permissionDao")
public class PermissionDaoImpl implements PermissionDao{

    private final String NAMESPACE = "com.mingguo.avarua.casual.account.service.repository.dao.PermissionDao.";

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int addPermission(Permission permission) {
        sqlSessionTemplate.insert(NAMESPACE + "addPermission", permission);
        return permission.getId() == null ? 0 : permission.getId();
    }

    @Override
    public int deletePermissionByIds(List<Integer> ids) {
        return sqlSessionTemplate.delete(NAMESPACE + "deletePermissionByIds", ids);
    }

    @Override
    public int updatePermission(Permission permission) {
        return sqlSessionTemplate.update(NAMESPACE + "updatePermission", permission);
    }

    @Override
    public int getPermissionCount() {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getPermissionCount");
    }

    @Override
    public int getPermissionCountByName(String permissionName) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getPermissionCountByName", permissionName);
    }

    @Override
    public Permission getPermissionById(Integer id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getPermissionById", id);
    }

    @Override
    public List<Permission> getPermissionListByIds(List<Integer> ids) {
        return sqlSessionTemplate.selectList(NAMESPACE + "getPermissionListByIds", ids);
    }

    @Override
    public List<Permission> getPermissionList(Integer page, Integer pageSize) {
        Map<String, Integer> parameters = new HashMap<String, Integer>();
        parameters.put("offset", (page - 1) * pageSize);
        parameters.put("limit", pageSize);
        return sqlSessionTemplate.selectList(NAMESPACE + "getPermissionList", parameters);
    }

    @Override
    public List<Permission> getPermissionListByName(String permissionName, Integer page, Integer pageSize) {
        Map parameters = Collections.emptyMap();;
        parameters.put("permissionName", permissionName);
        parameters.put("offset", (page - 1) * pageSize);
        parameters.put("limit", pageSize);
        return sqlSessionTemplate.selectList(NAMESPACE + "getPermissionListByName", parameters);
    }
}
