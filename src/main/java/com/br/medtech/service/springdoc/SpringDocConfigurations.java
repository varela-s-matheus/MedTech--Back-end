package com.br.medtech.service.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                .addSecuritySchemes("bearer-key",
                new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("MedTech Back-End")
                        .description("API Rest da aplicação MedTech, contendo diversas funcionalidades de CRUD de administradores, clínicas, médicos, pacientes, além de agendamento de consultas")
                        .contact(new Contact()
                                .name("Developer")
                                .url("https://www.linkedin.com/in/varela-s-matheus/")));
    }


}
