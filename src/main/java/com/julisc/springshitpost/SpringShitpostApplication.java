package com.julisc.springshitpost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
public class SpringShitpostApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringShitpostApplication.class, args);
    }

}
