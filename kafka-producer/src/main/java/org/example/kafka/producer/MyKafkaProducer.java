package org.example.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> template;

    public void sendMessage(String key, String message) {
        template.send("t-multi-partitions", key, message);
    }
}