package com.watchlux.service;

import com.watchlux.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    void saveAll(List<OrderItem> items);
    List<OrderItem> findByOrderId(int orderId);
    void deleteByOrderId(int orderId);
}
