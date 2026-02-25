package org.pedro.huelladecarbonospringboot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI huellaOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Huella de Carbono")
                        .version("1.0")
                        .description("API REST para gestion de huella de carbono, actividades y habitos.")
                        .contact(new Contact().name("Pedro")));
    }
}
