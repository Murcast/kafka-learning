package org.example.consumer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.message.OrderMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderConsumer {

    @SneakyThrows
    @KafkaListener(topics = "t-commodity-order")
    public void listen(OrderMessage message) {
        var total = message.getPrice() * message.getQuantity();
        log.info("Processing order {}. Credit card number {}. Total price : {}",
                message.getOrderNumber(), message.getCreditCardNumber(), total);
    }
}
