package com.chenjx.redwars.service.impl;

import com.chenjx.redwars.dao.UserDao;
import com.chenjx.redwars.domain.User;
import com.chenjx.redwars.service.UserService;
import com.chenjx.redwars.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by chenjx 2018/11/26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Long saveUser(User user) {
        user.setId(null);
        user.setPassword(MD5.md5(user.getPassword()));
        return userDao.saveUser(user);
    }

    @Override
    public Long updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public Long deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    @Override
    public User findByName(String username) {
        return userDao.findByName(username);
    }

    @Override
    public void login(String username, String password) {
        userDao.findByName(username);
    }
}
