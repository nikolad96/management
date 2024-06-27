package com.example.management.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    @RabbitListener(queues = "queue-name")
    public void receiveMessage(String message)
    {
        logger.info("Received message: {}", message);
    }
}
