package com.chenjx.redwars.dao;

import com.chenjx.redwars.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    User findByName(@Param("username") String username);

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
    User findById(@Param("id") Long id);

    Long saveUser(User user);

    Long updateUser(User user);

    Long deleteUser(Long id);

}
