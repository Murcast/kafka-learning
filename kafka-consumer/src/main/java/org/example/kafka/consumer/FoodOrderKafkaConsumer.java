package org.example.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.kafka.entity.FoodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FoodOrderKafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @KafkaListener(topics = "t-food-order")
    public void listen(String data) {
        var result = objectMapper.readValue(data, FoodOrder.class);
        if (result.getAmount() > 7) {
            throw new IllegalArgumentException("to many orders : actual amount - " + result.getAmount());
        }
        log.info("Order : {}", result);
    }
}
