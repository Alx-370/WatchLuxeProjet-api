package com.watchlux.dao.impl;

import com.watchlux.dao.CustomerDao;
import com.watchlux.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private final JdbcTemplate jdbcTemplate;

    public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Customer> customerMapper = (rs, rowNum) -> new Customer(
            rs.getInt("id"),
            rs.getString("email"),
            rs.getString("name"),
            rs.getString("password"),
            rs.getString("role")
    );

    @Override
    public Optional<Customer> findByEmail(String email) {
        String sql = "SELECT * FROM customer WHERE email = ?";
        return jdbcTemplate.query(sql, customerMapper, email).stream().findFirst();
    }

    @Override
    public Optional<Customer> findById(int id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return jdbcTemplate.query(sql, customerMapper, id).stream().findFirst();
    }

    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customer (email, name, password, role) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, customer.getEmail(), customer.getName(), customer.getPassword(), customer.getRole());
    }
}
