package org.example.kafka;

import org.example.kafka.producer.MyKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class KafkaSpringBootProducer implements CommandLineRunner {

    @Autowired
    private MyKafkaProducer producer;
    public static void main(String[] args) {
        SpringApplication.run(KafkaSpringBootProducer.class);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            producer.sendMessage("Murcast " + ThreadLocalRandom.current().nextInt());
            Thread.sleep(1000);
        }
    }
}
