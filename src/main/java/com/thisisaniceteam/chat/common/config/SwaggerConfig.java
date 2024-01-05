package com.thisisaniceteam.chat.common.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;


@Configuration
public class SwaggerConfig {
    SecurityScheme securityScheme = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .in(SecurityScheme.In.HEADER)
            .name("Authorization");

    SecurityRequirement securityRequirement = new SecurityRequirement()
            .addList("bearerAuth");

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .externalDocs(null)
                .servers(null)
                .security(null)
                .tags(null)
                .paths(null)
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", securityScheme))
                .security(Collections.singletonList(securityRequirement));
    }

    private Info apiInfo() {
        return new Info()
                .title("this-is-a-nice-backend-chat")
                .description("Chat API 명세서")
                .contact(new Contact()
                        .name("LimSeHwan")
                        .url("https://github.com/Torres-09")
                        .email("dla612@naver.com"))
                .license(new License())
                .version("1.0.0");
    }
}
