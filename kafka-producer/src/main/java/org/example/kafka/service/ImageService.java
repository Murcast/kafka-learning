package org.example.kafka.service;

import org.example.kafka.entity.Image;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ImageService {

    private static AtomicInteger counter = new AtomicInteger();

    public Image createImage(String type) {
        return new Image(
                "img-" + counter.incrementAndGet(),
                ThreadLocalRandom.current().nextInt(1_000, 5_000),
                type
        );
    }
}
