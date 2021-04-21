package com.example.Employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket swaggerApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select() //api related information
                .paths(PathSelectors.any()) // path mapping
                .apis(RequestHandlerSelectors.basePackage("com.example")) //related api
                .build();
                /*.apiInfo(apiInfo());*/
    }


    /*private ApiInfo apiInfo() {
        return new ApiInfo(
                "akjahk",
                "aaaaa",
                "aaaa",
                "www",
                new Contact("jhgjgs","https://www.aahjjj","hsgss@gmail.com"),
                "kjahkj",
                Collections.emptyList());

    }*/
}
