
package com.watchlux.dao;

import com.watchlux.entity.OrderItem;
import java.util.List;

public interface OrderItemDao {
    void createBatch(List<OrderItem> items);
    List<OrderItem> findByOrderId(Long orderId);
}
