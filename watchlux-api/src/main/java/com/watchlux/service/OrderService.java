package com.watchlux.service;

import com.watchlux.model.Order;

import java.util.List;

public interface OrderService {
    void createOrder(Order order);
    List<Order> getOrdersByCustomerEmail(String email);
    List<Order> getAllOrders();
}
