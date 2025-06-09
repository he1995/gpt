package com.example.common.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaySuccessMessage {
    private String orderId;
}
