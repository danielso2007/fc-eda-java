package com.estudoeda.balances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SuppressWarnings("PMD.UseUtilityClass")
@SpringBootApplication(scanBasePackages = "com.estudoeda.balances")
@EnableJpaRepositories
@EnableJpaAuditing
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
