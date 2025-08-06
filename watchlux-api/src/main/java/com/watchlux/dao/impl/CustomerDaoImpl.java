package com.watchlux.dao.impl;

import com.watchlux.dao.CustomerDao;
import com.watchlux.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        List<Customer> results = jdbcTemplate.query(sql, customerMapper, email);
        return results.stream().findFirst();
    }

    @Override
    public Optional<Customer> findById(int id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        List<Customer> results = jdbcTemplate.query(sql, customerMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customer (email, name, password, role) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, customer.getEmail(), customer.getName(), customer.getPassword(), customer.getRole());
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customer SET email = ?, name = ?, password = ?, role = ? WHERE id = ?";
        jdbcTemplate.update(sql, customer.getEmail(), customer.getName(), customer.getPassword(), customer.getRole(), customer.getId());
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, customerMapper);
    }
}
