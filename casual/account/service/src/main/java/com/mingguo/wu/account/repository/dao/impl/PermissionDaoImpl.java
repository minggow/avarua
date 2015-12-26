package com.mingguo.wu.account.repository.dao.impl;

import com.mingguo.wu.account.model.Permission;
import com.mingguo.wu.account.repository.dao.PermissionDao;
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

    private final String NAMESPACE = "com.mingguo.wu.account.repository.dao.PermissionDao.";

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplateAccount;

    @Override
    public int addPermission(Permission permission) {
        sqlSessionTemplateAccount.insert(NAMESPACE + "addPermission", permission);
        return permission.getId() == null ? 0 : permission.getId();
    }

    @Override
    public int deletePermissionByIds(List<Integer> ids) {
        return sqlSessionTemplateAccount.delete(NAMESPACE + "deletePermissionByIds", ids);
    }

    @Override
    public int updatePermission(Permission permission) {
        return sqlSessionTemplateAccount.update(NAMESPACE + "updatePermission", permission);
    }

    @Override
    public int getPermissionCount() {
        return sqlSessionTemplateAccount.selectOne(NAMESPACE + "getPermissionCount");
    }

    @Override
    public int getPermissionCountByName(String permissionName) {
        return sqlSessionTemplateAccount.selectOne(NAMESPACE + "getPermissionCountByName", permissionName);
    }

    @Override
    public Permission getPermissionById(Integer id) {
        return sqlSessionTemplateAccount.selectOne(NAMESPACE + "getPermissionById", id);
    }

    @Override
    public List<Permission> getPermissionListByIds(List<Integer> ids) {
        return sqlSessionTemplateAccount.selectList(NAMESPACE + "getPermissionListByIds", ids);
    }

    @Override
    public List<Permission> getPermissionList(Integer page, Integer pageSize) {
        Map<String, Integer> parameters = new HashMap<String, Integer>();
        parameters.put("offset", (page - 1) * pageSize);
        parameters.put("limit", pageSize);
        return sqlSessionTemplateAccount.selectList(NAMESPACE + "getPermissionList", parameters);
    }

    @Override
    public List<Permission> getPermissionListByName(String permissionName, Integer page, Integer pageSize) {
        Map parameters = Collections.emptyMap();;
        parameters.put("permissionName", permissionName);
        parameters.put("offset", (page - 1) * pageSize);
        parameters.put("limit", pageSize);
        return sqlSessionTemplateAccount.selectList(NAMESPACE + "getPermissionListByName", parameters);
    }
}
