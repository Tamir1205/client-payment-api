package com.example.clientpaymentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClientPaymentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientPaymentApiApplication.class, args);
    }

}
