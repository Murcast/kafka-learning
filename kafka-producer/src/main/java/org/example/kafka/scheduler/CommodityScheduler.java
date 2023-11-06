package org.example.kafka.scheduler;

import org.example.kafka.entity.Commodity;
import org.example.kafka.producer.CommodityKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CommodityScheduler {

    private final RestTemplate template = new RestTemplate();

    @Autowired
    private CommodityKafkaProducer producer;

//    @Scheduled(fixedRate = 5000)
    public void send() {
        template.exchange("http://localhost:8080/api/commodity/v1/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Commodity>>() {
                }).getBody().forEach(producer::sendMessage);
    }
}
