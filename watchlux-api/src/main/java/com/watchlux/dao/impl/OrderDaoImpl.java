package com.watchlux.dao.impl;

import com.watchlux.dao.OrderDao;
import com.watchlux.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private final JdbcTemplate jdbcTemplate;

    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Order> orderMapper = (rs, rowNum) -> {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
        order.setCustomerId(rs.getInt("customer_id"));
        return order;
    };

    @Override
    public void save(Order order) {
        String sql = "INSERT INTO orders (order_date, customer_id) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
            ps.setObject(1, order.getOrderDate());
            ps.setInt(2, order.getCustomerId());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            order.setId(key.intValue());
        }
    }

    @Override
    public List<Order> findByCustomerEmail(String email) {
        String sql = "SELECT o.* FROM orders o JOIN customer c ON o.customer_id = c.id WHERE c.email = ?";
        return jdbcTemplate.query(sql, orderMapper, email);
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM orders";
        return jdbcTemplate.query(sql, orderMapper);
    }
}
