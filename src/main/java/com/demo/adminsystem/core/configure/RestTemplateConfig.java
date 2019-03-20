package com.demo.adminsystem.core.configure;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:15
 * @version: V1.0
 * @detail: RestTemplateConfig 设置RestTemplate 模板
 **/
public class RestTemplateConfig {
    @Bean
    public RestTemplate buildRestTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        builder.setConnectTimeout(1000*10);
        builder.setReadTimeout(1000*60);
        return builder.build();
    }
}
