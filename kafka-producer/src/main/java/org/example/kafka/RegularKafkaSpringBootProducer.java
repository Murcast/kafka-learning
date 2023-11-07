package org.example.kafka;

import lombok.SneakyThrows;
import org.example.kafka.entity.Invoice;
import org.example.kafka.producer.RegularKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@SpringBootApplication
public class RegularKafkaSpringBootProducer implements CommandLineRunner {

    @Autowired
    private RegularKafkaProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(RegularKafkaSpringBootProducer.class);
    }

    @Override
    @SneakyThrows
    public void run(String... args) {
        for (int i = 0; i < 10_000; i++) {
            producer.send(new Invoice("invoice-" + i, ThreadLocalRandom.current().nextInt(1_000, 5_000), "USD"));
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
