package org.example;

import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class Client {

    @Autowired
    ClientEventPublisher rpcEventPublisher;

    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        System.out.println("Sending message from client to receiving exchange");
        rpcEventPublisher.sendMessage(new Message("message here".getBytes()));
    }
}
