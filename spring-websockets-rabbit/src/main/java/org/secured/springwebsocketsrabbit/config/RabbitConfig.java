package org.secured.springwebsocketsrabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.*;

@Configuration
public class RabbitConfig {
    // create a qeueu bean
    public final String QUEUE_NAME = "secured-websockets-queue";

    public final String EXCHANGE_NAME = "secured-websockets-exchange";


    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Queue retryQueue() {
        return new Queue("secured-websockets-retry-queue", true);
    }

    @Bean
    public Queue dlq() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", EXCHANGE_NAME);
        args.put("x-dead-letter-routing-key", "deadLetter-routing-key");
        return new Queue("secured-websockets-dlq", true, false, false, args);
    }


    // create a exchange bean
    @Bean
    public Exchange exchange() {
        return new DirectExchange(EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding retryBinding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(retryQueue()).to(exchange).with("retry-queue-routing-key").noargs();
    }

    @Bean
    public Binding undeliveredBinding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(dlq()).to(exchange).with("undelivered-queue-routing-key").noargs();
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("main-queue-routing-key").noargs();
    }

    // create a binding bean of Declarables
//    @Bean
//    public Declarables declarables() {
//        return new Declarables(queue(), exchange(), BindingBuilder.bind(queue()).to(exchange()).with("routing-key").noargs());
//    }



    @Bean
    public Jackson2JsonMessageConverter serializer() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(3);

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(1000);
        retryTemplate.setRetryPolicy(simpleRetryPolicy);
        return retryTemplate;
    }



    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, MessageConverter serializer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(serializer);
        factory.setDefaultRequeueRejected(false);
        return factory;
    }
}
