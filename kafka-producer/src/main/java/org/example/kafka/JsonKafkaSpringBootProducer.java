package org.example.kafka;

import org.example.kafka.entity.Employee;
import org.example.kafka.producer.MyJsonKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class JsonKafkaSpringBootProducer implements CommandLineRunner {

    @Autowired
    private MyJsonKafkaProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(JsonKafkaSpringBootProducer.class);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            producer.sendMessage(new Employee("emp-" + i, "Employee " + i, LocalDate.now()));
        }
    }
}
