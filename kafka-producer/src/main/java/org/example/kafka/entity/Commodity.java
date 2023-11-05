package org.example.kafka.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Commodity {
    private String name;
    private double price;
    private String measurement;
    private long timestamp;

    public void setPrice(double price) {
        this.price = Math.round(price * 100d) / 100d;
    }

    public Commodity(String name, double price, String measurement, long timestamp) {
        this.name = name;
        this.setPrice(price);
        this.measurement = measurement;
        this.timestamp = timestamp;
    }
}
