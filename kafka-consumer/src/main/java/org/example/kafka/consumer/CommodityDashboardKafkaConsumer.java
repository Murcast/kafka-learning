package org.example.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.kafka.entity.Commodity;
import org.example.kafka.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CommodityDashboardKafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "t-commodity", groupId = "cg-dashboard")
    public void consume(String commodity) throws Exception {
        var result = objectMapper.readValue(commodity, Commodity.class);
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(500, 2000));
        log.info("Dashboard Commodity : {}", result);
    }
}
