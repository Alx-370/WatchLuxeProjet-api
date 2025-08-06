package com.watchlux.dao.impl;

import com.watchlux.dao.ProductDao;
import com.watchlux.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoImpl implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Product> productMapper = (rs, rowNum) -> new Product(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getBigDecimal("price"),
            rs.getInt("stock"),
            rs.getString("image")
    );

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, productMapper);
    }

    @Override
    public Optional<Product> findById(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        List<Product> results = jdbcTemplate.query(sql, productMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO product (name, description, price, stock, image) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(),
                product.getStock(), product.getImage());
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE product SET name = ?, description = ?, price = ?, stock = ?, image = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(),
                product.getStock(), product.getImage(), product.getId());
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
