
package com.watchlux.mapper;

import com.watchlux.entity.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {
    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItem oi = new OrderItem();
        oi.setId(rs.getLong("id"));
        oi.setOrderId(rs.getLong("order_id"));
        oi.setProductId(rs.getLong("product_id"));
        oi.setQuantity(rs.getInt("quantity"));
        oi.setUnitPrice(rs.getDouble("unit_price"));
        try {
            oi.setProductName(rs.getString("product_name"));
        } catch (Exception ignored) {}
        return oi;
    }
}
