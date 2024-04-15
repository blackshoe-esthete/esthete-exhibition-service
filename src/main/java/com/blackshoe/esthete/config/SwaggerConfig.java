package com.blackshoe.esthete.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Esthete Exhibition API",
                version = "1.0.0",
                description = "Esthete"
        ),
        servers = @Server(url = "/")
)
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // JWT 보안 요구 사항을 추가합니다. 이는 인증이 필요한 API에 적용됩니다.
                .addSecurityItem(new SecurityRequirement().addList("JWT", new ArrayList<>()))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("JWT", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
//                .addSecurityItem(new SecurityRequirement().addList("CookieAuth", new ArrayList<>()))
//                .components(new io.swagger.v3.oas.models.Components()
//                        .addSecuritySchemes("CookieAuth", new SecurityScheme()
//                                .type(SecurityScheme.Type.APIKEY)
//                                .in(SecurityScheme.In.COOKIE)
//                                .name("refresh")));
    }
}
