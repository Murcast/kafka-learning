package org.example.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RebalancedKafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> template;

    private final AtomicInteger counter = new AtomicInteger();

//    @Scheduled(fixedRate = 1000)
    public void send() {
        var i = counter.incrementAndGet();
        template.send("t-rebalanced", "key-" + i, "message with id = " + i);
    }
}
