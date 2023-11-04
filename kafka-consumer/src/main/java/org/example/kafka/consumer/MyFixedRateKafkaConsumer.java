package org.example.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyFixedRateKafkaConsumer {

    @KafkaListener(topics = "t-fixedrate-2")
    public void readMessage(String message) {
        log.info("Consuming: {}", message);
    }
}