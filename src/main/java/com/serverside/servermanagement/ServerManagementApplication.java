package com.serverside.servermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServerManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerManagementApplication.class, args);
    }

}
