
package com.watchlux.dao;

import com.watchlux.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> findAll(String q);
    Optional<Product> findById(Long id);
    Product create(Product p);
    Product update(Long id, Product p);
    void delete(Long id);
    void decrementStock(Long id, int by);
}
