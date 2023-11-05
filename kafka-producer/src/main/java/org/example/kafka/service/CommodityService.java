package org.example.kafka.service;

import org.example.kafka.entity.Commodity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CommodityService {

    private static final Map<String, Commodity> COMMODITY_MAP = new HashMap<>();
    private static final String COPPER = "copper";
    private static final String GOLD = "gold";
    private static final double MIN_ADJ = 0.95d;
    private static final double MAX_ADJ = 1.05d;

    static {
        var timestamp = System.currentTimeMillis();
        COMMODITY_MAP.put(COPPER, new Commodity(COPPER, 19_567.78, "tonne", timestamp));
        COMMODITY_MAP.put(GOLD, new Commodity(GOLD, 5_293.71, "ounce", timestamp));
    }

    public Commodity getCommodity(String name) {
        if (!COMMODITY_MAP.containsKey(name)) {
            throw new IllegalArgumentException("invalid commodity : " + name);
        }
        var commodity = COMMODITY_MAP.get(name);
        commodity.setPrice(commodity.getPrice() * ThreadLocalRandom.current().nextDouble(MIN_ADJ, MAX_ADJ));
        commodity.setTimestamp(System.currentTimeMillis());
        return commodity;
    }

    public List<Commodity> getAllCommodities() {
        return COMMODITY_MAP.keySet().stream()
                .map(this::getCommodity)
                .toList();
    }
}
