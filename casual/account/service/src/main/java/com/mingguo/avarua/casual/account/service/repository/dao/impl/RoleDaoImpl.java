package com.mingguo.avarua.casual.account.service.repository.dao.impl;

import com.mingguo.avarua.casual.account.model.Role;
import com.mingguo.avarua.casual.account.service.repository.dao.RoleDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mingguo.wu on 2015/10/8.
 */
@Component("roleDao")
public class RoleDaoImpl implements RoleDao{

    private final String NAMESPACE = "com.mingguo.avarua.casual.account.service.repository.dao.RoleDao.";

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int addRole(Role role) {
        sqlSessionTemplate.insert(NAMESPACE + "addRole", role);
        return role.getId() == null ? 0 : role.getId();
    }

    @Override
    public int deleteRolesByIds(List<Integer> ids) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteRolesByIds", ids);
    }

    @Override
    public int updateRole(Role role) {
        return sqlSessionTemplate.update(NAMESPACE + "updateRole", role);
    }

    @Override
    public Integer getRoleCount() {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getRoleCount");
    }

    @Override
    public Role getRoleById(Integer id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getRoleById", id);
    }

    @Override
    public List<Role> getRolesByIds(List<Integer> ids) {
        return sqlSessionTemplate.selectList(NAMESPACE + "getRolesByIds", ids);
    }

    @Override
    public List<Role> getRoleList(Integer page, Integer pageSize) {
        Map<String, Integer> parameters = new HashMap<String, Integer>();
        parameters.put("offset", (page - 1) * pageSize);
        parameters.put("limit", pageSize);
        return sqlSessionTemplate.selectList(NAMESPACE + "getRoleList", parameters);
    }

    @Override
    public Integer getRoleCountByRoleName(String roleName) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getRoleCountByRoleName", roleName);
    }

    @Override
    public Integer getRoleCountByFullRoleName(String roleName) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getRoleCountByFullRoleName", roleName);
    }

    @Override
    public List<Role> getRolesByRoleName(String roleName, Integer page, Integer pageSize) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("roleName", roleName);
        parameters.put("offset", (page - 1) * pageSize);
        parameters.put("limit", pageSize);
        return sqlSessionTemplate.selectList(NAMESPACE + "getRolesByRoleName", parameters);
    }

}
