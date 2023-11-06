package org.example.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.kafka.entity.FoodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FoodOrderKafkaProducer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> template;

    @SneakyThrows
    public void send(FoodOrder order) {
        var json = objectMapper.writeValueAsString(order);
        template.send("t-food-order", json);
    }
}
