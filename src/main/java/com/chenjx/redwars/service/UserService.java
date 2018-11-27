package com.chenjx.redwars.service;

import com.chenjx.redwars.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create by chenjx 2018/11/26
 */
public interface UserService {

    /**
     * 获取城市信息列表
     *
     * @return
     */
    List<User> findAllUser();

    /**
     * 根据城市 ID，获取城市信息
     *
     * @param id
     * @return
     */
    User findById(Long id);

    Long saveUser(User user);

    Long updateUser(User user);

    Long deleteUser(Long id);

    User findByName(String username);

    void login(String username, String password);
}
