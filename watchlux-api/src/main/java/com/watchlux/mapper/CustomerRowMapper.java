
package com.watchlux.mapper;

import com.watchlux.entity.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer c = new Customer();
        c.setId(rs.getLong("id"));
        c.setEmail(rs.getString("email"));
        c.setName(rs.getString("name"));
        return c;
    }
}
