package com.elasticsearch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.elasticsearch.web"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(info())
                .enableUrlTemplating(true);
    }

    private ApiInfo info() {
        return new ApiInfo(
                "Api Documentation",
                "Api documentation for sample implementation of spring boot rest using elasticsearch",
                "1.0",
                "urn:tos",
                new Contact("Juan dela Cruz", "http://www.dummy.com", "jc@gmail.com"),
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>()
        );
    }
}
