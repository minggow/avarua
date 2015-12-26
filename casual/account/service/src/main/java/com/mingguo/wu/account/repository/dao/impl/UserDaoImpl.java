package com.mingguo.wu.account.repository.dao.impl;

import com.mingguo.wu.account.model.User;
import com.mingguo.wu.account.repository.dao.UserDao;
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

    private final String NAMESPACE = "com.mingguo.wu.account.repository.dao.UserDao.";

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplateAccount;

    @Override
    public int addUser(User user) {
        sqlSessionTemplateAccount.insert(NAMESPACE + "addUser", user);
        return user.getId()==null ? 0 : user.getId();
    }

    @Override
    public int updateUser(User user) {
        return sqlSessionTemplateAccount.update(NAMESPACE + "updateUser", user);
    }

    @Override
    public int deleteUsersByIds(List<Integer> idList) {
        return sqlSessionTemplateAccount.update(NAMESPACE + "deleteUsersByIds", idList);
    }

    @Override
    public User getUserById(Integer id) {
        return sqlSessionTemplateAccount.selectOne(NAMESPACE + "getUserById", id);
    }

    @Override
    public User getUserByLoginName(String loginName) {
        return sqlSessionTemplateAccount.selectOne(NAMESPACE + "getUserByLoginName", loginName);
    }

    @Override
    public List<User> getUsersByIds(List<Integer> ids) {
        return sqlSessionTemplateAccount.selectList(NAMESPACE + "getUsersByIds", ids);
    }

    @Override
    public int getCountByUserName(String userName) {
        return sqlSessionTemplateAccount.selectOne(NAMESPACE + "getCountByUserName", userName);
    }

    @Override
    public List<User> getUsersByUserName(String userName, Integer page, Integer pageSize) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userName", userName);
        parameters.put("offset", (page - 1) * pageSize);
        parameters.put("limit", pageSize);
        return sqlSessionTemplateAccount.selectList(NAMESPACE + "getUsersByUserName", parameters);
    }

    @Override
    public int getUserCount() {
        return sqlSessionTemplateAccount.selectOne(NAMESPACE + "getUserCount");
    }

    @Override
    public List<User> getUserList(Integer page, Integer pageSize) {
        Map<String, Integer> parameters = new HashMap<String, Integer>();
        parameters.put("offset", (page - 1) * pageSize);
        parameters.put("limit", pageSize);
        return sqlSessionTemplateAccount.selectList(NAMESPACE + "getUserList", parameters);
    }
}
