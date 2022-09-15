package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SSMPApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SSMPApplication.class, args);
            System.out.println("-------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
