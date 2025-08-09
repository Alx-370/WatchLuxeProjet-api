
package com.watchlux.dao.impl;

import com.watchlux.dao.OrderDao;
import com.watchlux.entity.Order;
import com.watchlux.mapper.OrderRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private final JdbcTemplate jdbc;

    public OrderDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Order create(Order o) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO `order`(customer_id, total) VALUES(?,?)",
                Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, o.getCustomerId());
            ps.setDouble(2, o.getTotal());
            return ps;
        }, kh);
        o.setId(kh.getKey().longValue());
        return o;
    }

    @Override
    public List<Order> findByCustomerEmail(String email) {
        String sql = "SELECT o.id, o.customer_id, o.total, o.created_at FROM `order` o " +
                "JOIN customer c ON c.id=o.customer_id WHERE c.email=? ORDER BY o.created_at DESC";
        return jdbc.query(sql, new OrderRowMapper(), email);
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT id, customer_id, total, created_at FROM `order` ORDER BY created_at DESC";
        return jdbc.query(sql, new OrderRowMapper());
    }
}
