package com.estudoeda.walletcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.estudoeda.walletcore")
@SuppressWarnings("PMD.UseUtilityClass")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
