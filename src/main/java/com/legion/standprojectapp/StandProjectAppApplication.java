package com.legion.standprojectapp;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class StandProjectAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(StandProjectAppApplication.class, args);
    }
}
