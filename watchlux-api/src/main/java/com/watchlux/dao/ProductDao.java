package com.watchlux.dao;

import com.watchlux.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> findAll();
    Optional<Product> findById(int id);
    void save(Product product);
    void update(Product product);
    void deleteById(int id);
}
