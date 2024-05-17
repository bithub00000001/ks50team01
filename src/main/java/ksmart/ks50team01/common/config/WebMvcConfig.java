package ksmart.ks50team01.common.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ksmart.ks50team01.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	 List<String> pathList = new ArrayList<String>();
    	 pathList.add("/favicon.ico");
    	 pathList.add("/common/css/**");
    	 pathList.add("/platform/build/**");
    	 pathList.add("/platform/css/**");
    	 pathList.add("/platform/images/**");
    	 pathList.add("/platform/js/**");
    	 pathList.add("/platform/vendors/**");
    	 pathList.add("/user/css/**");
    	 pathList.add("/user/img/**");
    	 pathList.add("/user/js/**");
    	 pathList.add("/user/plugins/**");
    	 pathList.add("/platform");
    	 pathList.add("/");
    	 
    	 
    	 registry.addInterceptor(loginInterceptor)
         		 .addPathPatterns("/platform/**")
         		 .excludePathPatterns(pathList);
    	 
    	WebMvcConfigurer.super.addInterceptors(registry);
    }
}