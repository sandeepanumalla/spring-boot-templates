package org.secured.springmailasync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringMailAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMailAsyncApplication.class, args);
    }

}
