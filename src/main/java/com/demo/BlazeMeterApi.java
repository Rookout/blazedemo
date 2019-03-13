package com.demo;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.rookout.rook.API;

@EnableSwagger2
@SpringBootApplication
public class BlazeMeterApi {
    public static void main(String[] args) {
        String ROOKOUT_TOKEN = System.getenv("ROOKOUT_TOKEN");
        if (ROOKOUT_TOKEN != null && !ROOKOUT_TOKEN.equals("")) {
            API.start();
        }
        SpringApplication.run(BlazeMeterApi.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any()).build().pathMapping("/")
                .apiInfo(apiInfo()).useDefaultResponseMessages(false);
    }

    @Bean
    ApiInfo apiInfo() {
        final ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("BlazeMeter Spring Boot API").version("1.0").license("(C) Copyright BlazeMeter")
                .description("List of all endpoints used in API");
        return builder.build();
    }
}