package com.watchlux.service.impl;

import com.watchlux.dao.OrderDao;
import com.watchlux.model.Order;
import com.watchlux.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void createOrder(Order order) {
        orderDao.save(order);
    }

    @Override
    public List<Order> getOrdersByCustomerEmail(String email) {
        return orderDao.findByCustomerEmail(email);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }
}
