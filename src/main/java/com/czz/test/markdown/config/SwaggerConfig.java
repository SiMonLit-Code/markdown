package com.czz.test.markdown.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * @author czz
 * @version 1.0
 * @date 2020/8/27 21:53
 */
//@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securitySchemes(asList(
                        new OAuth(
                                "petstore_auth",
                                asList(new AuthorizationScope("write_pets", "modify pets in your account"),
                                        new AuthorizationScope("read_pets", "read your pets")),
                                singletonList(new ImplicitGrant(new LoginEndpoint("http://petstore.swagger.io/api/oauth/dialog"), "tokenName"))
                        ),
                        new ApiKey("api_key", "api_key", "header")
                ))
                .select()
                .apis(RequestHandlerSelectors.basePackage("head.swagger2markup.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Petstore API")
                .description("Petstore API在线文档")
                .contact(new Contact("head", null, "761721453@qq.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}