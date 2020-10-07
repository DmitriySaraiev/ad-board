package com.saraiev.adboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class AdBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdBoardApplication.class, args);
    }

}
