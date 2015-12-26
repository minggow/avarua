package com.mingguo.wu.account.repository.dao.impl;

import com.mingguo.wu.account.model.UserRoleRelation;
import com.mingguo.wu.account.repository.dao.UserRoleRelationDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mingguo.wu on 2015/10/8.
 */
@Component("userRoleRelationDao")
public class UserRoleRelationDaoImpl implements UserRoleRelationDao{

    private final String NAMESPACE = "com.mingguo.wu.account.repository.dao.UserRoleRelationDao.";

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplateAccount;

    @Override
    public int addRelation(UserRoleRelation userRoleRelation) {
        sqlSessionTemplateAccount.insert(NAMESPACE + "addRelation", userRoleRelation);
        return userRoleRelation.getId() == null ? 0 : userRoleRelation.getId();
    }

    @Override
    public int updateRelation(UserRoleRelation userRoleRelation) {
        return sqlSessionTemplateAccount.update(NAMESPACE + "updateRelation", userRoleRelation);
    }

    @Override
    public int deleteRelationByIds(List<Integer> ids) {
        return sqlSessionTemplateAccount.delete(NAMESPACE + "deleteByIds", ids);
    }

    @Override
    public UserRoleRelation getRelationById(Integer id) {
        return sqlSessionTemplateAccount.selectOne(NAMESPACE + "getRelationById", id);
    }

    @Override
    public int deleteRelationByUserRole(Integer userId, Integer roleId) {
        Map<String, Integer> parameters = new HashMap<String, Integer>();
        parameters.put("userId", userId);
        parameters.put("roleId", roleId);
        return sqlSessionTemplateAccount.delete(NAMESPACE + "deleteByUserRole", parameters);
    }

    @Override
    public int getRelationCountByRoleId(Integer roleId) {
        return sqlSessionTemplateAccount.selectOne(NAMESPACE + "getCountByRoleId", roleId);
    }

    @Override
    public List<UserRoleRelation> getRelationsByRoleId(Integer roleId, Integer page, Integer pageSize) {
        Map<String, Integer> parameters = new HashMap<String, Integer>();
        parameters.put("roleId", roleId);
        parameters.put("offset", (page - 1) * pageSize);
        parameters.put("limit", pageSize);
        return sqlSessionTemplateAccount.selectList(NAMESPACE + "getRelationsByRoleId", parameters);
    }

    @Override
    public List<UserRoleRelation> getRelationsByUserIds(List<Integer> userIds) {
        return sqlSessionTemplateAccount.selectList(NAMESPACE + "getRelationsByUserIds", userIds);
    }

    @Override
    public List<UserRoleRelation> getRelationsByUserId(Integer userId) {
        return sqlSessionTemplateAccount.selectList(NAMESPACE + "getRelationsByUserId", userId);
    }

    @Override
    public UserRoleRelation getRelationByUserIdAndRoleId(int userId, int roleId) {
        Map<String, Integer> parameters = new HashMap<String, Integer>();
        parameters.put("roleId", roleId);
        parameters.put("userId", userId);
        return sqlSessionTemplateAccount.selectOne(NAMESPACE + "getRelationByUserIdAndRoleId", parameters);
    }
}
