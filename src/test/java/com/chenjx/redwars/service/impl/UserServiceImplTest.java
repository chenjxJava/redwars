package com.chenjx.redwars.service.impl;

import com.chenjx.redwars.domain.User;
import com.chenjx.redwars.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceImplTest {

    @Autowired
    public UserService userService;
    @Test
    public void findUserByUsername() {
        User userByUsername = userService.findById(2L);
        System.out.println(userByUsername);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("chenjx");
        user.setPassword("admin");
        System.out.println(userService.saveUser(user));
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(2L);
        user.setUsername("chenjx1");
        user.setPassword("admin1");
        System.out.println(userService.updateUser(user));
    }

    @Test
    public void findAllUser() {
        System.out.println(userService.findAllUser());
    }
}