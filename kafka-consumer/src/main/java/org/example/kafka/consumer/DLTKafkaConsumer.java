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
public class DLTKafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @KafkaListener(topics = "t-invoice", containerFactory = "deadContainerFactory", concurrency = "2")
    public void listen(String message) {
        var invoice = objectMapper.readValue(message, Invoice.class);

        if (invoice.getAmount() < 1) {
            throw new IllegalArgumentException("invalid invoice amount: " + invoice.getAmount());
        }
        log.info("received message : {}", invoice);
    }
}
