
package com.watchlux.service.impl;

import com.watchlux.dao.CustomerDao;
import com.watchlux.dao.OrderDao;
import com.watchlux.dao.OrderItemDao;
import com.watchlux.dao.ProductDao;
import com.watchlux.dto.CheckoutItemDTO;
import com.watchlux.dto.CheckoutRequest;
import com.watchlux.dto.OrderDTO;
import com.watchlux.dto.OrderItemDTO;
import com.watchlux.entity.Customer;
import com.watchlux.entity.Order;
import com.watchlux.entity.OrderItem;
import com.watchlux.entity.Product;
import com.watchlux.exception.NotFoundException;
import com.watchlux.exception.OutOfStockException;
import com.watchlux.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final CustomerDao customerDao;
    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final ProductDao productDao;

    public OrderServiceImpl(CustomerDao customerDao, OrderDao orderDao, OrderItemDao orderItemDao, ProductDao productDao) {
        this.customerDao = customerDao;
        this.orderDao = orderDao;
        this.orderItemDao = orderItemDao;
        this.productDao = productDao;
    }

    @Transactional
    @Override
    public OrderDTO checkout(CheckoutRequest req) {

        Customer customer = customerDao.findByEmail(req.getEmail()).orElseGet(() -> {
            Customer c = new Customer();
            c.setEmail(req.getEmail());
            c.setName(req.getName());
            return customerDao.create(c);
        });


        double total = 0.0;
        List<OrderItem> items = new ArrayList<>();
        for (CheckoutItemDTO it : req.getItems()) {
            Product p = productDao.findById(it.getProductId())
                    .orElseThrow(() -> new NotFoundException("Produit introuvable id=" + it.getProductId()));
            if (p.getStock() < it.getQuantity()) {
                throw new OutOfStockException("Produit " + p.getName() + " (id=" + p.getId() + ") stock=" + p.getStock() + ", demandé=" + it.getQuantity());
            }
            total += p.getPrice() * it.getQuantity();
            OrderItem oi = new OrderItem();
            oi.setProductId(p.getId());
            oi.setQuantity(it.getQuantity());
            oi.setUnitPrice(p.getPrice());
            items.add(oi);
        }


        Order order = new Order();
        order.setCustomerId(customer.getId());
        order.setTotal(total);
        order = orderDao.create(order);


        for (OrderItem oi : items) {
            oi.setOrderId(order.getId());
            productDao.decrementStock(oi.getProductId(), oi.getQuantity());
        }
        orderItemDao.createBatch(items);


        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setTotal(order.getTotal());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setItems(items.stream()
                .map(it -> new OrderItemDTO(it.getProductId(), null, it.getUnitPrice(), it.getQuantity()))
                .collect(Collectors.toList()));
        return dto;
    }

    @Override
    public List<OrderDTO> listByEmail(String email) {
        return orderDao.findByCustomerEmail(email).stream().map(o -> {
            OrderDTO dto = new OrderDTO();
            dto.setId(o.getId());
            dto.setTotal(o.getTotal());
            dto.setCreatedAt(o.getCreatedAt());
            dto.setItems(orderItemDao.findByOrderId(o.getId()).stream()
                    .map(oi -> new OrderItemDTO(oi.getProductId(), oi.getProductName(), oi.getUnitPrice(), oi.getQuantity()))
                    .collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> listAll() {
        return orderDao.findAll().stream().map(o -> {
            OrderDTO dto = new OrderDTO();
            dto.setId(o.getId());
            dto.setTotal(o.getTotal());
            dto.setCreatedAt(o.getCreatedAt());
            dto.setItems(orderItemDao.findByOrderId(o.getId()).stream()
                    .map(oi -> new OrderItemDTO(oi.getProductId(), oi.getProductName(), oi.getUnitPrice(), oi.getQuantity()))
                    .collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }
}
