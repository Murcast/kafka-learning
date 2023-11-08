package org.example.producer;

import org.example.message.DiscountMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DiscountProducer {

    @Autowired
    private KafkaTemplate<String, DiscountMessage> template;

    public void send(DiscountMessage message) {
        template.send("t-commodity-promotion", message);
    }
}
