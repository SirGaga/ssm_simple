package com.zhangjie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

// SpringMVC只扫描Controller，是一个子容器
// useDefaultFilters = false 禁用默认的扫描规则
@ComponentScan(value="com.zhangjie",includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
},useDefaultFilters = false)
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
    // 定制

    //视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //super.configureViewResolvers(registry);
        // 默认在"/WEB-INF/"下查找jsp，且后缀名是.jsp的
        //registry.jsp();
        // 自定义视图jsp路径和后缀名
        registry.jsp("/views/",".jsp");

    }
    //静态资源访问
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        //super.configureDefaultServletHandling(configurer);
        configurer.enable();

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //super.addResourceHandlers(registry);
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
    }

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);
        registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/**");
    }*/
}
