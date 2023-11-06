package org.example.kafka;

import org.example.kafka.entity.FoodOrder;
import org.example.kafka.producer.FoodOrderKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FoodOrderKafkaSpringBootProducer implements CommandLineRunner {

    @Autowired
    private FoodOrderKafkaProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(FoodOrderKafkaSpringBootProducer.class);
    }

    @Override
    public void run(String... args) {
        producer.send(new FoodOrder("meat", 3));
        producer.send(new FoodOrder("fish", 10));
        producer.send(new FoodOrder("pizza", 5));
    }
}
