package com.digipymes360.clienteFinal.swaggerconfi;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Scanventory - API de Cliente Final")
                .version("2.7 - Microservicio en termino")
                .description("API RESTful para administrar pedidos, productos, usuarios, soportes y reseñas. Diseñada para facilitar la integración y la gestión de inventario de forma eficiente.")
                .contact(new Contact()
                    .name("Equipo Scanventory")
                    .email("en.marin@duocuc.cl")
                )
                .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"))
            );
            
    }
}
