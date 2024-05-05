package org.secured.springwebsocketsrabbit;

import org.secured.springwebsocketsrabbit.config.RabbitConfig;
import org.secured.springwebsocketsrabbit.config.WebSocketsConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableAsync
@EnableWebSocketMessageBroker
@Import({RabbitConfig.class, WebSocketsConfig.class})
public class SpringWebsocketsRabbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebsocketsRabbitApplication.class, args);
    }


}
