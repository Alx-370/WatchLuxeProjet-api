package com.watchlux.dao;

import com.watchlux.model.OrderItem;

import java.util.List;

public interface OrderItemDao {
    void saveAll(List<OrderItem> items);
    List<OrderItem> findByOrderId(int orderId);
    void deleteByOrderId(int orderId);
}
