package org.example.dao;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.example.entity.Order.ORDER_HASH;


@Slf4j
@Repository
public class OrderDAO {

    @Autowired
    private RedisTemplate<String, Object> template;

    public Order save(Order order) {
        template.opsForHash().put(ORDER_HASH, order.getOrderId(), order);
        log.info("Order {} saved into database", order);
        return order;
    }

    public List<Order> getAll() {
        return template.<String, Order>opsForHash().values(ORDER_HASH);
    }

    public Order getById(String id) {
        return template.<String, Order>opsForHash().get(ORDER_HASH, id);
    }

    public void remove(String id) {
        template.<String, Order>opsForHash().delete(ORDER_HASH, id);
    }

}
