package com.codecool.codeoverflow;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CodeoverflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeoverflowApplication.class, args);
    }


    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
        };
    }
}