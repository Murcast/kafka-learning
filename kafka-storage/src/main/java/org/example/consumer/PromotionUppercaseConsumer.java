package org.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.example.message.PromotionMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PromotionUppercaseConsumer {

    @KafkaListener(topics = "t-commodity-promotion-uppercase")
    public void handlePromotions(PromotionMessage message) {
        log.info("Received Promotion message in uppercase : {}", message);
    }

}
