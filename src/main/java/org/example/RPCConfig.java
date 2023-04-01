package org.example;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RPCConfig {
    public static final String MESSAGE_EXCHANGE = "rabbit-message-exchange";
    public static final String MESSAGE_QUEUE = "rabbit-message-queue";
    public static final String DEAD_LETTER_EXCHANGE = "rabbit-dead-letter-exchange";
    public static final String DEAD_LETTER_QUEUE = "rabbit-dead-letter-queue";

    @Bean
    public Exchange messageExchange() {
        return new FanoutExchange(MESSAGE_EXCHANGE);
    }

    @Bean
    public Exchange deadLetterExchange() {
        return new FanoutExchange(DEAD_LETTER_EXCHANGE);
    }

    @Bean
    public Queue messageQueue() {
        return QueueBuilder
            .durable(MESSAGE_QUEUE)
            .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE)
            .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder
            .durable(DEAD_LETTER_QUEUE)
            .build();
    }

    @Bean
    public Binding bind(Exchange messageExchange, Queue messageQueue) {
        return BindingBuilder.bind(messageQueue).to(messageExchange).with("*").noargs();
    }

    @Bean
    public Binding bindDlq(Exchange deadLetterExchange, Queue deadLetterQueue) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with("*").noargs();
    }
}
