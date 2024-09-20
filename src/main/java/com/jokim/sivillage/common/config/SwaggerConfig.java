package com.jokim.sivillage.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // application.yaml 파일에서 swagger.url 값을 가져옴
    @Value("${swagger.url}")
    private String swaggerUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        String name = "Bearer Auth";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(name);
        Components components = new Components().addSecuritySchemes(name, new SecurityScheme()
                .name(name)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        );

        Server server = new Server();
        server.setUrl(swaggerUrl);

        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("S.I.VILLAGE Service API").version("v1").description("S.I.VILLAGE API Docs"))
                .addSecurityItem(securityRequirement)
                .components(components)
                .addServersItem(server);
    }
}
