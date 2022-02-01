package com.example.HockeyStandings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class HockeyApplication {
    public static void main(String[] args){
        SpringApplication.run(HockeyApplication.class, args);
    }
}