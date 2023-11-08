package org.example.action;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.api.request.OrderItemRequest;
import org.example.api.request.OrderRequest;
import org.example.dao.OrderDAO;
import org.example.entity.Order;
import org.example.entity.OrderItem;
import org.example.message.OrderMessage;
import org.example.producer.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderAction {

    @Autowired
    private OrderProducer orderProducer;
    @Autowired
    private OrderDAO orderRepository;

    public Order convertToOrder(OrderRequest request) {
        var order = new Order();

        order.setOrderDateTime(LocalDateTime.now());
        order.setCreditCardNumber(request.getCreditCardNumber());
        order.setOrderLocation(request.getOrderLocation());
        var orderNumber = RandomStringUtils.randomAlphanumeric(8);
        order.setOrderNumber(orderNumber.toUpperCase());

        var items = request.getItems().stream().map(this::convertToOrderItem).toList();

        order.setItems(items);
        order.setOrderId(request.getCreditCardNumber() + "-" + orderNumber);

        return order;
    }

    private OrderItem convertToOrderItem(OrderItemRequest request) {
        var orderItem = new OrderItem();

        orderItem.setItemName(request.getItemName());
        orderItem.setPrice(request.getPrice());
        orderItem.setQuantity(request.getQuantity());

        return orderItem;
    }

    public void saveToDatabase(Order order) {
        orderRepository.save(order);
    }

    public void publishToKafka(Order order) {
        for (OrderItem orderItem : order.getItems()) {
            var orderMessage = new OrderMessage();

            orderMessage.setItemName(orderItem.getItemName());
            orderMessage.setPrice(orderItem.getPrice());
            orderMessage.setQuantity(orderItem.getQuantity());

            orderMessage.setOrderNumber(order.getOrderNumber());
            orderMessage.setOrderLocation(order.getOrderLocation());
            orderMessage.setOrderDateTime(order.getOrderDateTime());
            orderMessage.setCreditCardNumber(order.getCreditCardNumber());

            orderProducer.send(orderMessage);
        }
    }
}
