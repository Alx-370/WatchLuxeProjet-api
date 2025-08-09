
package com.watchlux.mapper;

import com.watchlux.entity.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order o = new Order();
        o.setId(rs.getLong("id"));
        o.setCustomerId(rs.getLong("customer_id"));
        o.setTotal(rs.getDouble("total"));
        o.setCreatedAt(rs.getTimestamp("created_at").toInstant());
        return o;
    }
}
