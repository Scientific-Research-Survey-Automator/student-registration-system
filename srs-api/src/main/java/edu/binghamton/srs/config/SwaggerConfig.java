package edu.binghamton.srs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    private static final String BASE_PACKAGE_NAME = "edu.binghamton.srs";
    private static final String API_V1 = "/api/v1";

    @Value("${university.name}")
    private String university;

    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfoV1())
                .groupName("API_V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE_NAME))
                .paths(PathSelectors.regex(API_V1 + ".*"))
                .build();
    }

    private ApiInfo apiInfoV1() {
        return new ApiInfoBuilder()
                .title(String.format("Student Registration System for %s", StringUtils.capitalize(university)))
                .description("REST APIs for interacting with student registration system")
                .build();
    }

}
