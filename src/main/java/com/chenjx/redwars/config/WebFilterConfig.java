package com.chenjx.redwars.config;

import com.chenjx.redwars.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * create by chenjx 2018/10/13
 */
@Configuration
public class WebFilterConfig {

    /**
     * 跨域问题解决
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CorsFilter());
        registration.addUrlPatterns("/*");
        //registration.addInitParameter("paramName", "paramValue");
        registration.setName("corsFilter");
        registration.setOrder(1);
        return registration;
    }
}
