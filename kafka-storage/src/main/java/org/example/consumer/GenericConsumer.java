package org.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.example.message.DiscountMessage;
import org.example.message.PromotionMessage;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@KafkaListener(topics = "t-commodity-promotion")
public class GenericConsumer {

    @KafkaHandler
    public void handlePromotions(PromotionMessage message) {
        log.info("Received Promotion message : {}", message);
    }

    @KafkaHandler
    public void handleDiscounts(DiscountMessage message) {
        log.info("Received Discount message : {}", message);
    }
}
