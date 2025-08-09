
package com.watchlux.dao.impl;

import com.watchlux.dao.OrderItemDao;
import com.watchlux.entity.OrderItem;
import com.watchlux.mapper.OrderItemRowMapper;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {

    private final JdbcTemplate jdbc;

    public OrderItemDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void createBatch(List<OrderItem> items) {
        jdbc.batchUpdate("INSERT INTO order_item(order_id, product_id, quantity, unit_price) VALUES(?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        OrderItem it = items.get(i);
                        ps.setLong(1, it.getOrderId());
                        ps.setLong(2, it.getProductId());
                        ps.setInt(3, it.getQuantity());
                        ps.setDouble(4, it.getUnitPrice());
                    }

                    @Override
                    public int getBatchSize() { return items.size(); }
                });
    }

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        String sql = "SELECT oi.id, oi.order_id, oi.product_id, oi.quantity, oi.unit_price, p.name AS product_name " +
                "FROM order_item oi JOIN product p ON p.id=oi.product_id WHERE oi.order_id=?";
        return jdbc.query(sql, new OrderItemRowMapper(), orderId);
    }
}
