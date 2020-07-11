package com.bdqn.myappinfo.configs;

import com.bdqn.myappinfo.interceptor.BDUserLoginInterceptor;
import com.bdqn.myappinfo.interceptor.DevUserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private DevUserLoginInterceptor devUserLoginInterceptor;
    @Autowired
    private BDUserLoginInterceptor bdUserLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(devUserLoginInterceptor).addPathPatterns("/dev/flatform/**");
        registry.addInterceptor(bdUserLoginInterceptor).addPathPatterns("/manager/backend/**");
    }
}
