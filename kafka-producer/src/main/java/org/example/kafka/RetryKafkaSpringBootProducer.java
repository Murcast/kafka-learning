package org.example.kafka;

import org.example.kafka.producer.RetryKafkaProducer;
import org.example.kafka.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RetryKafkaSpringBootProducer implements CommandLineRunner {

    @Autowired
    private ImageService imageService;
    @Autowired
    private RetryKafkaProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(RetryKafkaSpringBootProducer.class);
    }

    @Override
    public void run(String... args) {
        producer.send(imageService.createImage("png"), 0);
        producer.send(imageService.createImage("svg"), 0);
        producer.send(imageService.createImage("bmp"), 0);
        producer.send(imageService.createImage("jpg"), 1);
        producer.send(imageService.createImage("jpeg"), 1);
        producer.send(imageService.createImage("gif"), 1);
    }
}
