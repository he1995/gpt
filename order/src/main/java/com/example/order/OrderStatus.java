package com.example.order;

import lombok.Getter;

@Getter
public enum OrderStatus {
    UNPAID(0),
    CANCELLED(1),
    PAID(2),
    FINISHED(3);

    private final int code;

    OrderStatus(int code) {
        this.code = code;
    }

}
