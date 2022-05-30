package com.example.tourdefrance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
public class TourDeFranceApplication {


    public static void main(String[] args) {
        SpringApplication.run(TourDeFranceApplication.class, args);
    }

}
