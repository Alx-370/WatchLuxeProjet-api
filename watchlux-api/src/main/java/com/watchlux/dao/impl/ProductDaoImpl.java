
package com.watchlux.dao.impl;

import com.watchlux.dao.ProductDao;
import com.watchlux.entity.Product;
import com.watchlux.exception.NotFoundException;
import com.watchlux.mapper.ProductRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoImpl implements ProductDao {

    private final JdbcTemplate jdbc;

    public ProductDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Product> findAll(String q) {
        if (q != null && !q.isBlank()) {
            String like = "%" + q.trim().toLowerCase() + "%";
            return jdbc.query(
                "SELECT id, name, description, price, stock FROM product WHERE LOWER(name) LIKE ? OR LOWER(description) LIKE ? ORDER BY id DESC",
                new ProductRowMapper(), like, like);
        }
        return jdbc.query("SELECT id, name, description, price, stock FROM product ORDER BY id DESC", new ProductRowMapper());
    }

    @Override
    public Optional<Product> findById(Long id) {
        List<Product> list = jdbc.query("SELECT id, name, description, price, stock FROM product WHERE id=?",
                new ProductRowMapper(), id);
        return list.stream().findFirst();
    }

    @Override
    public Product create(Product p) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO product(name, description, price, stock) VALUES(?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStock());
            return ps;
        }, kh);
        p.setId(kh.getKey().longValue());
        return p;
    }

    @Override
    public Product update(Long id, Product p) {
        int rows = jdbc.update("UPDATE product SET name=?, description=?, price=?, stock=? WHERE id=?",
                p.getName(), p.getDescription(), p.getPrice(), p.getStock(), id);
        if (rows == 0) throw new NotFoundException("Produit introuvable id=" + id);
        p.setId(id);
        return p;
    }

    @Override
    public void delete(Long id) {
        jdbc.update("DELETE FROM product WHERE id=?", id);
    }

    @Override
    public void decrementStock(Long id, int by) {
        int rows = jdbc.update("UPDATE product SET stock = stock - ? WHERE id=? AND stock >= ?",
                by, id, by);
        if (rows == 0) {
            throw new NotFoundException("Stock insuffisant ou produit introuvable id=" + id);
        }
    }
}
