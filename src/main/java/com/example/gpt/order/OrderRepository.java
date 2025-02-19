package com.example.gpt.order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderItem, String> {
}
