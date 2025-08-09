
package com.watchlux.dao;

import com.watchlux.entity.Order;
import java.util.List;

public interface OrderDao {
    Order create(Order o);
    List<Order> findByCustomerEmail(String email);
    List<Order> findAll();
}
