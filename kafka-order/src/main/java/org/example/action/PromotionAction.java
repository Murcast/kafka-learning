package org.example.action;

import org.example.api.request.PromotionRequest;
import org.example.message.PromotionMessage;
import org.example.producer.PromotionProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PromotionAction {

    @Autowired
    private PromotionProducer promotionProducer;

    public void publishToKafka(PromotionRequest promotion) {
        promotionProducer.send(new PromotionMessage(promotion.getPromotionCode()));
    }
}
