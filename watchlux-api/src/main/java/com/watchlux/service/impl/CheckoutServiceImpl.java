package com.watchlux.service.impl;

import com.watchlux.dao.CustomerDao;
import com.watchlux.dao.OrderDao;
import com.watchlux.dao.OrderItemDao;
import com.watchlux.dao.ProductDao;
import com.watchlux.exception.InsufficientStockException;
import com.watchlux.exception.ResourceNotFoundException;
import com.watchlux.model.Customer;
import com.watchlux.model.Order;
import com.watchlux.model.OrderItem;
import com.watchlux.model.Product;
import com.watchlux.service.CheckoutService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerDao customerDao;
    private final ProductDao productDao;
    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;

    public CheckoutServiceImpl(CustomerDao customerDao, ProductDao productDao,
                               OrderDao orderDao, OrderItemDao orderItemDao) {
        this.customerDao = customerDao;
        this.productDao = productDao;
        this.orderDao = orderDao;
        this.orderItemDao = orderItemDao;
    }

    @Override
    @Transactional
    public void checkout(String email, List<OrderItem> items) {
        Customer customer = customerDao.findByEmail(email);
        if (customer == null) {
            customer = new Customer();
            customer.setEmail(email);
            customerDao.save(customer);
        }

        for (OrderItem item : items) {
            Product product = productDao.findById(item.getProductId());
            if (product == null) {
                throw new ResourceNotFoundException("Produit non trouvé avec ID : " + item.getProductId());
            }
            if (product.getStock() < item.getQuantity()) {
                throw new InsufficientStockException("Stock insuffisant pour le produit : " + product.getName());
            }

            product.setStock(product.getStock() - item.getQuantity());
            productDao.update(product);

            item.setPrice(product.getPrice());
        }

        Order order = new Order();
        order.setCustomerId(customer.getId());
        order.setOrderDate(LocalDateTime.now());
        orderDao.save(order);

        for (OrderItem item : items) {
            item.setOrderId(order.getId());
        }
        orderItemDao.saveAll(items);
    }
}
