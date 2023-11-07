package org.example.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.kafka.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetryKafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @KafkaListener(topics = "t-retry", containerFactory = "retryContainerFactory", concurrency = "2")
    public void listen(String message) {
        var image = objectMapper.readValue(message, Image.class);

        if (image.getType().equals("svg")) {
            throw new IllegalArgumentException("invalid image type: " + image.getType());
        }
        log.info("received message : {}", image);
    }
}
