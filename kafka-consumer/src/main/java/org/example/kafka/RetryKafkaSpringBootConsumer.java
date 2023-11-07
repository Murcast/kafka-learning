package org.example.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RetryKafkaSpringBootConsumer {
    public static void main(String[] args) {
        SpringApplication.run(RetryKafkaSpringBootConsumer.class);
    }
}
