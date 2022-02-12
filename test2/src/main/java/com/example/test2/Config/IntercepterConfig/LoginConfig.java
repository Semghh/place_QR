package com.example.test2.Config.IntercepterConfig;

import com.example.test2.Interceptor.LoginInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

//@Configuration
//public class LoginConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        List<String> excludePath=new ArrayList<>();
//        excludePath.add("/Admin/login");
//
//        registry.addInterceptor(new LoginInterceptor())
//                .excludePathPatterns(excludePath)
//                .addPathPatterns("/**");
//    }
//}
