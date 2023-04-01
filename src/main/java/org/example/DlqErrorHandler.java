package org.example;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static org.example.RPCConfig.DEAD_LETTER_QUEUE;

@Component
public class DlqErrorHandler {
    @RabbitListener(queues = DEAD_LETTER_QUEUE)
    public void handleError(@Payload Message event) {
        System.out.println("Handling error in DLQ");
    }
}
