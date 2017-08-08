package com.wish.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                 // 文档标题
				.title("wish")
                // 文档描述
				.description("https://github.com/handexing").termsOfServiceUrl("https://github.com/handexing")
                .version("v1")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 指定controller存放的目录路径
				.apis(RequestHandlerSelectors.basePackage("com.wish.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
