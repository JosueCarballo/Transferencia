package main.java.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Transferencias Bancarias")
                        .version("1.0")
                        .description("API para realizar transferencias entre cuentas bancarias")
                        .contact(new Contact()
                                .name("Sistema Bancario")
                                .email("contacto@sistemabancario.com")));
    }
}