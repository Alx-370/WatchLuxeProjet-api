package com.watchlux.service.impl;

import com.watchlux.dao.OrderItemDao;
import com.watchlux.model.OrderItem;
import com.watchlux.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemDao orderItemDao;

    public OrderItemServiceImpl(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    @Override
    public void saveAll(List<OrderItem> items) {
        orderItemDao.saveAll(items);
    }

    @Override
    public List<OrderItem> findByOrderId(int orderId) {
        return orderItemDao.findByOrderId(orderId);
    }
}
