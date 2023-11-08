package org.example.producer;

import lombok.extern.slf4j.Slf4j;
import org.example.message.PromotionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PromotionProducer {

    @Autowired
    private KafkaTemplate<String, PromotionMessage> template;

    public void send(PromotionMessage message) {
        try {
            var result = template.send("t-commodity-promotion", message).get();
            log.info("Message `{}` processed successfully", message);
        } catch (Exception e) {
            log.info("Exception `{}` occurred during processing of message `{}`", e.getMessage(), message);
        }
    }
}
