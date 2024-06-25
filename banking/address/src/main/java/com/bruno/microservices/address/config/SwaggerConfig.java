package com.bruno.microservices.address.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bruno.microservices.address.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Address Microservice API",
                "This API is used in the Address Microservice",
                "Version 1.0",
                "https:https://github.com/brazil-bruno/banking-ecommerce-microservices-devops",
                new Contact("Bruno Camargo", "https://github.com/brazil-bruno/banking-ecommerce-microservices-devops/tree/main/banking/address", "bruno.brazil2022@gmail.com"),
                "Allowed for testing",
                "https://https://github.com/brazil-bruno/banking-ecommerce-microservices-devops",
                Collections.emptyList()
        );
    }
}
