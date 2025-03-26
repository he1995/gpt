package com.example.mask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.common.entity")
public class MaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaskApplication.class, args);
    }

}
