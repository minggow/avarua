package com.mingguo.avarua.casual.account.service.repository.dao.impl;

import com.mingguo.avarua.casual.account.model.User;
import com.mingguo.avarua.casual.account.service.repository.dao.UserDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mingguo.wu on 2015/10/8.
 */
@Component("userDao")
public class UserDaoImpl implements UserDao{

    private final String NAMESPACE = "com.mingguo.avarua.casual.account.service.repository.dao.UserDao.";

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int addUser(User user) {
        sqlSessionTemplate.insert(NAMESPACE + "addUser", user);
        return user.getId()==null ? 0 : user.getId();
    }

    @Override
    public int updateUser(User user) {
        return sqlSessionTemplate.update(NAMESPACE + "updateUser", user);
    }

    @Override
    public int deleteUsersByIds(List<Integer> idList) {
        return sqlSessionTemplate.update(NAMESPACE + "deleteUsersByIds", idList);
    }

    @Override
    public User getUserById(Integer id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getUserById", id);
    }

    @Override
    public User getUserByLoginName(String loginName) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getUserByLoginName", loginName);
    }

    @Override
    public List<User> getUsersByIds(List<Integer> ids) {
        return sqlSessionTemplate.selectList(NAMESPACE + "getUsersByIds", ids);
    }

    @Override
    public int getCountByUserName(String userName) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getCountByUserName", userName);
    }

    @Override
    public List<User> getUsersByUserName(String userName, Integer page, Integer pageSize) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userName", userName);
        parameters.put("offset", (page - 1) * pageSize);
        parameters.put("limit", pageSize);
        return sqlSessionTemplate.selectList(NAMESPACE + "getUsersByUserName", parameters);
    }

    @Override
    public int getUserCount() {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getUserCount");
    }

    @Override
    public List<User> getUserList(Integer page, Integer pageSize) {
        Map<String, Integer> parameters = new HashMap<String, Integer>();
        parameters.put("offset", (page - 1) * pageSize);
        parameters.put("limit", pageSize);
        return sqlSessionTemplate.selectList(NAMESPACE + "getUserList", parameters);
    }
}
