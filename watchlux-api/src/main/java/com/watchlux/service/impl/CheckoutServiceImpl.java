package com.watchlux.service.impl;

import com.watchlux.dao.CustomerDao;
import com.watchlux.dao.OrderDao;
import com.watchlux.dao.OrderItemDao;
import com.watchlux.dao.ProductDao;
import com.watchlux.exception.InsufficientStockException;
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
    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final ProductDao productDao;

    public CheckoutServiceImpl(CustomerDao customerDao, OrderDao orderDao,
                               OrderItemDao orderItemDao, ProductDao productDao) {
        this.customerDao = customerDao;
        this.orderDao = orderDao;
        this.orderItemDao = orderItemDao;
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public void checkout(String email, List<OrderItem> items) {
        Customer customer = customerDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec cet email : " + email));

        for (OrderItem item : items) {
            Product product = productDao.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Produit introuvable, id=" + item.getProductId()));

            if (product.getStock() < item.getQuantity()) {
                throw new InsufficientStockException("Stock insuffisant pour le produit : " + product.getName());
            }
        }
        Order order = new Order();
        order.setCustomerId(customer.getId());
        order.setOrderDate(LocalDateTime.now());
        orderDao.save(order);


        for (OrderItem item : items) {
            item.setOrderId(order.getId());
            orderItemDao.saveAll(List.of(item));

            Product product = productDao.findById(item.getProductId()).get();
            product.setStock(product.getStock() - item.getQuantity());
            productDao.update(product);
        }
    }
}
