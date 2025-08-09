
package com.watchlux.dao.impl;

import com.watchlux.dao.CustomerDao;
import com.watchlux.entity.Customer;
import com.watchlux.mapper.CustomerRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private final JdbcTemplate jdbc;

    public CustomerDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        List<Customer> list = jdbc.query("SELECT id, email, name FROM customer WHERE email=?",
                new CustomerRowMapper(), email);
        return list.stream().findFirst();
    }

    @Override
    public Customer create(Customer c) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO customer(email, name) VALUES(?,?)",
                Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getEmail());
            ps.setString(2, c.getName());
            return ps;
        }, kh);
        c.setId(kh.getKey().longValue());
        return c;
    }
}
