package org.example.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.kafka.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommodityNotificationKafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

//    @KafkaListener(topics = "t-commodity", groupId = "cg-notification")
    public void consume(String commodity) throws Exception {
        log.info("Notification Commodity : {}", objectMapper.readValue(commodity, Commodity.class));
    }
}
