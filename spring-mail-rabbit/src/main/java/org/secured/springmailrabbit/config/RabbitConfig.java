package org.secured.springmailrabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue("secured.mail.queue", true);
    }

    @Bean
    public Exchange defaultExchange() {
        return new DirectExchange("secured.mail.exchange", true, false);
    }

    @Bean
    public Declarables declarables() {
        return new Declarables(queue(), defaultExchange(),
                BindingBuilder.bind(queue()).to(defaultExchange()).with("secured.mail.routingKey").noargs()
                );
    }

    @Bean
    public Jackson2JsonMessageConverter serializer() {
         return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, MessageConverter serializer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setDefaultRequeueRejected(false);
        factory.setMessageConverter(serializer);
        return factory;
    }

}
