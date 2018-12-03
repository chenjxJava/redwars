package com.chenjx.redwars.config;

import com.chenjx.redwars.interceptor.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * create by chenjx 2018/8/22
 *
 * 注册拦截器
 */
//@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(getTokenInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/js/**",
                "/swagger-ui.html", "/Quick_BI/**", "/fonts/**", "/Client/tologin", "/hello");
    }

    @Bean
    public HandlerInterceptor getTokenInterceptor() {
        return new Interceptor();
    }
}