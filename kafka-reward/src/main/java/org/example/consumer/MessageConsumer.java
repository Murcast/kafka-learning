package org.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.example.message.OrderMessage;
import org.example.message.OrderReplyMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class MessageConsumer {

    @KafkaListener(topics = "t-commodity-order")
    @SendTo("t-commodity-order-reply")
    public OrderReplyMessage listen(ConsumerRecord<String, OrderMessage> consumerRecord) {
        var headers = consumerRecord.headers();
        var value = consumerRecord.value();

        log.info("Received message : {}", value);
        log.info("Headers :");
        headers.forEach(h -> log.info("    key : {}, value : {}", h.key(), h.value()));

        Header bonusHeaderValue;
        String bonus;

        if ((bonusHeaderValue = headers.lastHeader("surpriseBonus")) == null || ObjectUtils.isEmpty(bonusHeaderValue.value())) {
            bonus = "0";
        } else {
            bonus = new String(bonusHeaderValue.value());
        }
        var bonusAsNumber = Integer.parseInt(bonus);
        log.info("Final price : {}", (1d - (bonusAsNumber / 100d)) * value.getPrice() * value.getQuantity());

        return new OrderReplyMessage(value.getOrderNumber());
    }
}
