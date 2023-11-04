package org.example.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaConsumer {

    @KafkaListener(topics = "t-hello")
    public void readMessage(String message) {
        System.out.println(message);
    }
}
