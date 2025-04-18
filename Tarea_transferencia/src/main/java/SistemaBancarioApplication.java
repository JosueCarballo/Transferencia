package main.java.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "main.java")
@EntityScan("main.java.entidades")
@EnableJpaRepositories("main.java.datos")
public class SistemaBancarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaBancarioApplication.class, args);
    }
}