package com.chenjx.redwars;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.chenjx.redwars.dao")
@EnableSwagger2
public class RedwarsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RedwarsApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
