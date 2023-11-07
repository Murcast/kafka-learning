package org.example.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.kafka.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DLTKafkaProducer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> template;

    @SneakyThrows
    public void send(Invoice invoice) {
        template.send("t-invoice", invoice.getAmount() % 2,
                invoice.getInvoiceNumber(), objectMapper.writeValueAsString(invoice));
    }
}
