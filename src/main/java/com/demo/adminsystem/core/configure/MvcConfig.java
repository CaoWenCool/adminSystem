package com.demo.adminsystem.core.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 14:57
 * @version: V1.0
 * @detail:
 **/
@Configuration
public class MvcConfig implements WebMvcConfigurer{

    /**
     * 跨域访问
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
//        registry.addMapping("/**");//允许所有的请求访问
//        registry.addMapping("/api/**")
//                .allowCredentials(true)
//                .allowedOrigins("http://domain2.com")
//                .allowedMethods("POST","GET");
    }

    /**
     * 格式化
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyy-MM-dd HH:mm:ss"));
    }

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AdminsystemApplication())
//                .addPathPatterns("/admin/**");
    }

    /**
     * URI到视图的映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        // 解决 SWAGGER 404报错
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
