package com.config.confgserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfgServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfgServerApplication.class, args);
    }

}
