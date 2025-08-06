package com.watchlux.dao.impl;

import com.watchlux.dao.OrderItemDao;
import com.watchlux.model.OrderItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {

    private final JdbcTemplate jdbcTemplate;

    public OrderItemDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<OrderItem> orderItemMapper = (rs, rowNum) -> {
        OrderItem item = new OrderItem();
        item.setId(rs.getInt("id"));
        item.setOrderId(rs.getInt("order_id"));
        item.setProductId(rs.getInt("product_id"));
        item.setQuantity(rs.getInt("quantity"));
        return item;
    };

    @Override
    public void saveAll(List<OrderItem> items) {
        String sql = "INSERT INTO order_item (order_id, product_id, quantity) VALUES (?, ?, ?)";
        for (OrderItem item : items) {
            jdbcTemplate.update(sql, item.getOrderId(), item.getProductId(), item.getQuantity());
        }
    }

    @Override
    public List<OrderItem> findByOrderId(int orderId) {
        String sql = "SELECT * FROM order_item WHERE order_id = ?";
        return jdbcTemplate.query(sql, orderItemMapper, orderId);
    }

    @Override
    public void deleteByOrderId(int orderId) {
        String sql = "DELETE FROM order_item WHERE order_id = ?";
        jdbcTemplate.update(sql, orderId);
    }
}
