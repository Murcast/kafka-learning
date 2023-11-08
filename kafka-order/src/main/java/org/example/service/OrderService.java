package org.example.service;

import org.example.action.OrderAction;
import org.example.api.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderAction orderAction;

    public String saveOrder(OrderRequest request) {
        var order = orderAction.convertToOrder(request);
        orderAction.saveToDatabase(order);
        orderAction.publishToKafka(order);
        return order.getOrderNumber();
    }
}
