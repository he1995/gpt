package com.example.gpt.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Cacheable("orders")
    public Iterable<OrderItem> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void createOrder(OrderItem orderItem) {
        orderRepository.save(orderItem);
    }
}
