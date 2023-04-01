package org.example;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static org.example.RPCConfig.*;

@Component
public class Server {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = MESSAGE_QUEUE)
    public void receive(@Payload Message message) {
        System.out.println("Reading message at server");
        throw new AmqpRejectAndDontRequeueException("error");
    }
}
