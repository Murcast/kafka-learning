package org.example.kafka;

import org.example.kafka.producer.MyJson2KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CommodityKafkaSpringBootProducer implements CommandLineRunner {

    @Autowired
    private MyJson2KafkaProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(CommodityKafkaSpringBootProducer.class);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
