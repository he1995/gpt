package com.example.gpt.order;

public interface IOrderService {
    Iterable<OrderItem> getOrders();
    void createOrder(OrderItem orderItem);
}
