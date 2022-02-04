package com.example.woc.config;

import com.example.woc.interceptor.AuthAPIInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author NuoTian
 * @date 2022/2/5
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authAPIInterceptor())
                .addPathPatterns("/**");
    }

    @Bean
    public AuthAPIInterceptor authAPIInterceptor() {
        return new AuthAPIInterceptor();
    }
}
