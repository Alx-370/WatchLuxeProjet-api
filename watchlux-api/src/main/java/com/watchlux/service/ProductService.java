
package com.watchlux.service;

import com.watchlux.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    List<ProductDTO> list(String q);
    ProductDTO get(Long id);
    ProductDTO create(ProductDTO dto);
    ProductDTO update(Long id, ProductDTO dto);
    void delete(Long id);
}
