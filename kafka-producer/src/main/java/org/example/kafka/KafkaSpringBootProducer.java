package org.example.kafka;

import org.example.kafka.producer.MyKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaSpringBootProducer implements CommandLineRunner {

    @Autowired
    private MyKafkaProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaSpringBootProducer.class);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 30; i++) {
            var key = "key-" + (i % 4);
            var value = "value " + i + " with key " + key;
            producer.sendMessage(key, value);
        }
    }
}
