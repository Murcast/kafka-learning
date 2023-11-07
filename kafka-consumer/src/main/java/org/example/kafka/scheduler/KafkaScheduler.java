package org.example.kafka.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class KafkaScheduler {

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @Scheduled(cron = "0 40 22 * * ?")
    public void pause() {
        registry.getListenerContainer("t-invoice.one").pause();
    }

    @Scheduled(cron = "0 42 22 * * ?")
    public void resume() {
        registry.getListenerContainer("t-invoice.one").resume();
    }

}
