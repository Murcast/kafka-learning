package org.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.example.message.OrderReplyMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderReplyConsumer {

    @KafkaListener(topics = "t-commodity-order-reply")
    public void reconcile(OrderReplyMessage message) {
        log.info("Confirm receiving of message : {}", message.getOrderNumber());
    }
}
