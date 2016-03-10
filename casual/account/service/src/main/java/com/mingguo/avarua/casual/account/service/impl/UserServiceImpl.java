package com.mingguo.avarua.casual.account.service.impl;

import com.mingguo.avarua.casual.account.model.User;
import com.mingguo.avarua.casual.account.service.UserService;
import com.mingguo.avarua.casual.account.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mingguo.wu on 2016/3/10.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public int addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public boolean deleteUser(Integer id) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public User getUserById(Integer id) {
        return null;
    }
}
