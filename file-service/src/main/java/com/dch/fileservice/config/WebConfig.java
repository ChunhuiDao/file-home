package com.dch.fileservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 登录拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 此方法用来注册拦截器，写好的拦截器需要通过这里添加注册才能生效
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
         * addPathPatterns("/**") 表示拦截所有的请求
         * excludePathPatterns("","") 中为不拦截路径
         */
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/user/register", "/login/**");
    }
}
