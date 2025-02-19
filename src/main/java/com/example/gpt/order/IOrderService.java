package com.example.gpt.order;

public interface IOrderService {
    Iterable<OrderInfo> getOrders();
    void createOrder(OrderInfo orderInfo);
    void cancelOrder(String orderId);
}
