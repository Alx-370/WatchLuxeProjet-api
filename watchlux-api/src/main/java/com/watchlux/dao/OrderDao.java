package com.watchlux.dao;

import com.watchlux.model.Order;

import java.util.List;

public interface OrderDao {
    void save(Order order);
    List<Order> findByCustomerEmail(String email);
    List<Order> findAll();
}
