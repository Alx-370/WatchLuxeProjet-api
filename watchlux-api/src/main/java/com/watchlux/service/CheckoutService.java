package com.watchlux.service;

import com.watchlux.model.OrderItem;

import java.util.List;

public interface CheckoutService {
    void checkout(String email, List<OrderItem> items);
}
