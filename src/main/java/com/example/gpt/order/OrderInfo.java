package com.example.gpt.order;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class OrderInfo {
    @Id
    private String id;

    private String name;

    private String createTime;

    private String delivery;

    private OrderStatus status;

    private String price;

    private String payMethod;

    private String email;

    private String username;
}
