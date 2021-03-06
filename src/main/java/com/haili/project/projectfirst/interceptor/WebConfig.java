package com.haili.project.projectfirst.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器
 * @author Created by hailitortoise on 2020-04-02
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**")
         *
         * addPathPatterns("/**") 对哪些地址做拦截
         * excludePathPatterns("/admin/**") 忽略那些地址，不做拦截
         */
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\avatar\\";
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            registry.addResourceHandler("/images/avatar/**")
                    .addResourceLocations("file:"+filePath);
        }
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}