package com.example.gpt.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Cacheable("orders")
    public Page<OrderInfo> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public void createOrder(OrderInfo orderInfo) {
        orderRepository.save(orderInfo);
    }

    @Override
    public void cancelOrder(String orderId) {
        OrderInfo order = orderRepository.findById(orderId).get();
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    @Override
    public Page<OrderInfo> findByEmail(String email, Pageable pageable) {
        return orderRepository.findAllByEmail(email, pageable);
    }
}
