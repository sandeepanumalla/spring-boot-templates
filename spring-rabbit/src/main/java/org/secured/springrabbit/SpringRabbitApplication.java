package org.secured.springrabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class SpringRabbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRabbitApplication.class, args);
    }

}
