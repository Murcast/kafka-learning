package org.example.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.example.message.OrderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
public class OrderProducer {

    @Autowired
    private KafkaTemplate<String, OrderMessage> template;

    public void send(OrderMessage message) {
        template.send(createProducerRecord(message))
                .whenCompleteAsync((res, ex) -> {
                    if (ex != null) {
                        log.warn("While processing record : {}, exception had been thrown : {}", res.getProducerRecord(), ex.getMessage());
                    } else {
                        log.info("Processing completed successfully for record : {}", res.getProducerRecord().value());
                    }
                });
        log.info("Just a dummy message to proof that complete ran asynchronously");
    }

    private ProducerRecord<String, OrderMessage> createProducerRecord(OrderMessage message) {
        var bonus = StringUtils.startsWithIgnoreCase(message.getOrderLocation(), "A") ? 25 : 15;
        return new ProducerRecord<>("t-commodity-order", null, message.getOrderNumber(), message,
                List.of(new RecordHeader("surpriseBonus", Integer.toString(bonus).getBytes())));
    }
}
