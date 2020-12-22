package com.oxford.http.interceptor;

import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置WebConfig
 *
 * @author Chova
 * @date 2020/12/22
 */
public class WebConfig implements WebMvcConfigurer {

    private LoginInterceptor loginInterceptor;

    public WebConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    /**
     * 添加拦截器
     *
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 需要拦截的路径
        String[] addPathPatterns = {"/user/**"};

        // 不需要拦截的路径
        String[] excludePathPatterns = {"/admin/**"};

        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);
        registration.addPathPatterns(addPathPatterns);
        registration.excludePathPatterns(excludePathPatterns);
    }
}
