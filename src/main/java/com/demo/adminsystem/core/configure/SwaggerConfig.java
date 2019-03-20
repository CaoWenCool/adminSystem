package com.demo.adminsystem.core.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:10
 * @version: V1.0
 * @detail:
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("WEB-EDI & 进出口系统 API文档")
                .description("记录　WEB-EDI & 进出口系统 API文档　详细")
                .termsOfServiceUrl("http://git.yaoqian.tech")
                .contact("yanbumo@163.com")
                .version("1.0")
                .build();
    }
}
