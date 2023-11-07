package org.example.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PauseResumeSpringBootConsumer {
    public static void main(String[] args) {
        SpringApplication.run(PauseResumeSpringBootConsumer.class);
    }
}
