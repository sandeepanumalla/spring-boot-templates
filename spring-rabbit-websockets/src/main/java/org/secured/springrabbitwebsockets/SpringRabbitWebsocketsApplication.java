package org.secured.springrabbitwebsockets;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class SpringRabbitWebsocketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRabbitWebsocketsApplication.class, args);
	}

}
