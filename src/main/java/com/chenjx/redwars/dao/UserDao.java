package com.chenjx.redwars.dao;

import com.chenjx.redwars.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    User findUserByUsername(@Param("username") String username);
}
