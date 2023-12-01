package com.thisisaniceteam.chat.common.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .externalDocs(null)
                .servers(null)
                .security(null)
                .tags(null)
                .paths(null)
                .components(new Components());
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
