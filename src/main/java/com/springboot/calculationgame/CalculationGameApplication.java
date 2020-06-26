package com.springboot.calculationgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CalculationGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculationGameApplication.class, args);
    }

}
