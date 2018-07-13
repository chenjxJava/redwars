package com.chenjx.redwars;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.chenjx.redwars.dao")
public class RedwarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedwarsApplication.class, args);
    }
}
