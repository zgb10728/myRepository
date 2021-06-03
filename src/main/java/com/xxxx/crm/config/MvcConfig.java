package com.xxxx.crm.config;

import com.xxxx.crm.interceptors.NoLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public NoLoginInterceptor noLoginInterceptor(){
        return new NoLoginInterceptor();
    }

    /*** 添加拦截器 * @param registry */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 需要一个实现HandlerInterceptor接口的拦截器实例，这里使用的是 NoLoginInterceptor
        registry.addInterceptor(noLoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index","/user/login","/css/**","/images/**","/js/**","/lib/**");
        // 用于设置拦截器的过滤路径规则
    }
}
