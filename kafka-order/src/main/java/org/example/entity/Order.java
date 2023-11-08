package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(Order.ORDER_HASH)
public class Order implements Serializable {

    public static final String ORDER_HASH = "Order";

    @Id
    private String orderId;
    private String orderNumber;
    private String orderLocation;
    private LocalDateTime orderDateTime;
    private String creditCardNumber;
    private List<OrderItem> items;
}
