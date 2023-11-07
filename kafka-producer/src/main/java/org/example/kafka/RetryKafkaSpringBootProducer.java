package org.example.kafka;

import org.example.kafka.entity.Invoice;
import org.example.kafka.producer.DLTKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ThreadLocalRandom;

@EnableScheduling
@SpringBootApplication
public class RetryKafkaSpringBootProducer implements CommandLineRunner {

    @Autowired
    private DLTKafkaProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(RetryKafkaSpringBootProducer.class);
    }

    @Override
    public void run(String... args) {
        for (int i = 0; i < 10; i++) {
            var invoice = new Invoice("invoice-" + i, ThreadLocalRandom.current().nextInt(1_000, 5_000), "USD");
            if (i > 5) {
                invoice.setAmount(0);
            }
            producer.send(invoice);
        }
    }
}
