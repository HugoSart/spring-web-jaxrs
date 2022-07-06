package com.hsartori.spring.web.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hsartori.spring.web")
public class DummyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DummyApplication.class);
    }

}
