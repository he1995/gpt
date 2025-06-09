package com.example.order;

public record OrderRequest(
        String name,
        String email,
        String price,
        String delivery,
        String payMethod,
        String username){}
