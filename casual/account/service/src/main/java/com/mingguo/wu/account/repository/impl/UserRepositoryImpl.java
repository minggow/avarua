package com.mingguo.wu.account.repository.impl;

import com.mingguo.wu.account.model.Role;
import com.mingguo.wu.account.model.User;
import com.mingguo.wu.account.model.UserRoleRelation;
import com.mingguo.wu.account.repository.UserRepository;
import com.mingguo.wu.account.repository.dao.RoleDao;
import com.mingguo.wu.account.repository.dao.UserDao;
import com.mingguo.wu.account.repository.dao.UserRoleRelationDao;
import com.mingguo.wu.account.util.PasswordEncodeUtil;
import com.mingguo.wu.infra.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mingguo.wu on 2015/10/8.
 */
public class UserRepositoryImpl implements UserRepository{


    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleRelationDao userRoleRelationDao;

    public int addUser(User user) {

        if (user == null || StringUtil.isEmpty(user.getLoginName())
                || StringUtil.isEmpty(user.getPassword())) {
            return 0;
        }
        user.setLoginName(user.getLoginName().trim());
        user.setPassword(PasswordEncodeUtil.encode(user.getPassword()));
        return userDao.addUser(user);
    }

    public int updateUser(User user) {
        if (null == user || StringUtil.isEmpty(user.getPassword())) {
            return 0;
        }
        user.setPassword(PasswordEncodeUtil.encode(user.getPassword()));
        return userDao.updateUser(user);
    }

    public int deleteUser(List<Integer> ids) {
        if(CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        return userDao.deleteUsersByIds(ids);
    }

    public User getUserById(Integer userId) {
        if(null == userId || userId < 1) {
            return null;
        }
        return userDao.getUserById(userId);
    }

    public User getUserWithRoleByUserId(Integer userId) {
        User user = getUserById(userId);
        if (user != null) {
            List<UserRoleRelation> userRoleRelationList = userRoleRelationDao.getRelationsByUserId(userId);
            if (!CollectionUtils.isEmpty(userRoleRelationList)) {
                // append role info
                List<Integer> roleIds = new ArrayList<Integer>(userRoleRelationList.size());
                for (UserRoleRelation userRoleRelation : userRoleRelationList) {
                    roleIds.add(userRoleRelation.getRoleId());
                }
                List<Role> roleList = roleDao.getRolesByIds(roleIds);
                if (!CollectionUtils.isEmpty(roleList)) {
                    user.getRoles().addAll(roleList);
                }
            }
        }
        return user;
    }

    public boolean isUserOwnRole(Integer userId, Integer roleId) {
        return userRoleRelationDao.getRelationByUserIdAndRoleId(userId, roleId) != null;
    }

    public User getUserWithPermissionByUserId(Integer userId) {
        User user = getUserById(userId);
        if (user != null) {
            List<UserRoleRelation> userRoleRelationList = userRoleRelationDao.getRelationsByUserId(userId);
            if (!CollectionUtils.isEmpty(userRoleRelationList)) {
                // append role info
                List<Integer> roleIdList = Collections.emptyList();;
                for (UserRoleRelation userRoleRelation : userRoleRelationList) {
                    roleIdList.add(userRoleRelation.getRoleId());
                }
                List<Role> roleList = roleDao.getRolesByIds(roleIdList);
                if (!CollectionUtils.isEmpty(roleIdList)) {
                    user.getRoles().addAll(roleList);
                    //append permission list
//                    List<Integer> permissionList = rolePermissionRelationDao.getPermissionListByRoles(ruleIdList);
//                    if (permissionList != null && !permissionList.isEmpty()) {
//                        user.setPermissionList(permissionList);
//                    }
                }

            }
        }
        return user;
    }
}
