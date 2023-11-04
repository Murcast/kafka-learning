package org.example.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MyMultiThreadingKafkaConsumer {

    @KafkaListener(topics = "t-multi-partitions", concurrency = "4")
    public void consume(ConsumerRecord<String, String> rec) throws InterruptedException {
        log.info("Key : {}, Partition : {}, Message : {}", rec.key(), rec.partition(), rec.value());
        TimeUnit.SECONDS.sleep(1);
    }
}
