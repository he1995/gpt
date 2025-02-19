package com.example.gpt.order;

import lombok.Getter;

@Getter
public enum OrderStatus {
    UNPAID(1),
    CANCELLED(2),
    PAID(3),
    FINISHED(4);

    private final int code;

    OrderStatus(int code) {
        this.code = code;
    }

}
