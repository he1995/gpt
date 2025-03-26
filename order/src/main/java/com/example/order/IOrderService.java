package com.example.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
    Page<OrderInfo> getOrders(Pageable pageable);
    void createOrder(OrderInfo orderInfo);
    void cancelOrder(String orderId);
    Page<OrderInfo> findByEmail(String email, Pageable pageable);
}
