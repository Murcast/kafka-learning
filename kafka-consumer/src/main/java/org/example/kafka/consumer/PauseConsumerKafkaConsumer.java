package org.example.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.kafka.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PauseConsumerKafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @KafkaListener(id = "t-invoice.one", topics = "t-invoice")
    public void listen1(String message) {
        var invoice = objectMapper.readValue(message, Invoice.class);

        log.info("From first consumer : received message : {}", invoice);
    }

    @SneakyThrows
    @KafkaListener(topics = "t-invoice")
    public void listen2(String message) {
        var invoice = objectMapper.readValue(message, Invoice.class);

        log.info("From second consumer : received message : {}", invoice);
    }
}
