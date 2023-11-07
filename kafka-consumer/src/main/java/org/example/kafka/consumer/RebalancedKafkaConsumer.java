package org.example.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RebalancedKafkaConsumer {

//    @KafkaListener(topics = "t-rebalanced", concurrency = "1")
    public void listen(ConsumerRecord<String, String> record) {
        log.info("Key : {}, Partition : {}, Message : {}", record.key(), record.partition(), record.value());
    }
}
