package org.example.producer;

import lombok.extern.slf4j.Slf4j;
import org.example.message.OrderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderProducer {

    @Autowired
    private KafkaTemplate<String, OrderMessage> template;

    public void send(OrderMessage message) {
        template.send("t-commodity-order", message.getOrderNumber(), message)
                .whenCompleteAsync((res, ex) -> {
                    if (ex != null) {
                        log.warn("While processing record : {}, exception had been thrown : {}", res.getProducerRecord(), ex.getMessage());
                    } else {
                        log.info("Processing completed successfully for record : {}", res.getProducerRecord().value());
                    }
                });
        log.info("Just a dummy message to proof that complete ran asynchronously");
    }
}
