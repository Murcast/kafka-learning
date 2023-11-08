package org.example;

import org.example.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class KafkaOrder implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(KafkaOrder.class, args);
    }

    @Autowired
    private OrderDAO dao;

    @Override
    public void run(String... args) throws Exception {
//        for (int i = 0; i < 10; i++) {
//            dao.save(new Order("order-id-" + i, String.valueOf(i), "Samara",
//                    LocalDateTime.now(), "1234 5678 9010 1112",
//                    List.of(
//                            new OrderItem(i, "book", 100, 5),
//                            new OrderItem(i + 100, "tape", 79, 2)
//                    )));
//        }
//        System.out.println("Get by id (1) : " + dao.getById("order-id-0"));
//        System.out.println("Get by id (2) : " + dao.getById("order-id-7"));
//
//        System.out.println("Get all : " + dao.getAll());
//
//        System.out.println("Delete by id");
//        dao.remove("order-id-7");
//        System.out.println("Get by id (3) : " + dao.getById("order-id-7"));

    }
}