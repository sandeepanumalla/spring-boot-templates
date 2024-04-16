package org.secured.springrabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queueBuilder() {
        return new Queue("secured-rabbit-queue", true);
    }

    @Bean
    public Exchange exchangeBuilder() {
        return new DirectExchange("secured-rabbit-exchange", true, false);
    }

    @Bean
    public Declarables declarableBuilder() {
        return new Declarables(queueBuilder(),
                exchangeBuilder(),
                BindingBuilder.bind(queueBuilder()).to(exchangeBuilder()).with("secured-rabbit-routing-key").noargs()
        );
    }



}
