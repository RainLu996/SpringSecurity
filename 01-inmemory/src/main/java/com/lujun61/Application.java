package com.lujun61;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) //排除 Secuirty 的配置，让他不启用
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
