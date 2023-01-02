package com.imdbsecondedition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@AutoConfiguration
@EnableJdbcRepositories
public class ImdbSecondEditionApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImdbSecondEditionApplication.class, args);
    }
}
