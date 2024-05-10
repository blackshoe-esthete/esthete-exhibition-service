package com.blackshoe.esthete.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info().title("Esthete Exhibition Service API")
                        .description("Esthete Exhibition Service LIST")
                        .version("0.0.1"))
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .security(Arrays.asList(securityRequirement))
                .servers(Arrays.asList(
                        //new io.swagger.v3.oas.models.servers.Server().url("https://user-api.esthete.com").description("Production server"),
                        new io.swagger.v3.oas.models.servers.Server().url("http://43.201.228.22:8030").description("Test Server"),
                        new io.swagger.v3.oas.models.servers.Server().url("http://localhost:8030").description("Local development server")
                ));
    }
}

