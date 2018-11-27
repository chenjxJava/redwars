package com.chenjx.redwars.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * create by chenjx 2018/11/26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedis {
    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    @Test
    public void demo() {
        stringRedisTemplate.opsForValue().set("user", "name");
        String user = stringRedisTemplate.opsForValue().get("user");
        System.out.println(user);
    }

}
