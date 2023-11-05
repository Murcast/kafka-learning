package org.example.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.kafka.entity.Commodity;
import org.example.kafka.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CommodityKafkaProducer {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private KafkaTemplate<String, String> template;

    @SneakyThrows
    public void sendMessage(Commodity commodity) {
        template.send("t-commodity", commodity.getName(), objectMapper.writeValueAsString(commodity));
    }
}
