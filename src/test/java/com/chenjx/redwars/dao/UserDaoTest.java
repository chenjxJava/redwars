package com.chenjx.redwars.dao;

import com.chenjx.redwars.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    public UserDao userDao;

    @Test
    public void findUserByUsername() {
        User userByUsername = userDao.findByName("admin");
        System.out.println(userByUsername);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("chenjx");
        user.setPassword("admin");
        System.out.println(userDao.saveUser(user));
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(2L);
        user.setUsername("chenjx1");
        user.setPassword("admin1");
        System.out.println(userDao.updateUser(user));
    }

    @Test
    public void findAllUser() {
        System.out.println(userDao.findAllUser());
    }
}