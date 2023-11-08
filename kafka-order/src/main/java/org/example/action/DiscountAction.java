package org.example.action;

import org.example.api.request.DiscountRequest;
import org.example.message.DiscountMessage;
import org.example.producer.DiscountProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscountAction {

    @Autowired
    private DiscountProducer producer;

    public void publishToKafka(DiscountRequest request) {
        producer.send(new DiscountMessage(request.getDiscountCode(), request.getDiscountPercentage()));
    }
}
