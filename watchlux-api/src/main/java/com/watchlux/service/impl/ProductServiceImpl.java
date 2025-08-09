
package com.watchlux.service.impl;

import com.watchlux.dao.ProductDao;
import com.watchlux.dto.ProductDTO;
import com.watchlux.entity.Product;
import com.watchlux.exception.NotFoundException;
import com.watchlux.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    private static ProductDTO toDTO(Product p) {
        ProductDTO dto = new ProductDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setPrice(p.getPrice());
        dto.setStock(p.getStock());
        return dto;
    }

    private static Product toEntity(ProductDTO dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setStock(dto.getStock());
        return p;
    }

    @Override
    public List<ProductDTO> list(String q) {
        return productDao.findAll(q).stream().map(ProductServiceImpl::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO get(Long id) {
        Product p = productDao.findById(id).orElseThrow(() -> new NotFoundException("Produit introuvable id=" + id));
        return toDTO(p);
    }

    @Override
    public ProductDTO create(ProductDTO dto) {
        Product p = productDao.create(toEntity(dto));
        return toDTO(p);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO dto) {
        Product p = toEntity(dto);
        p = productDao.update(id, p);
        return toDTO(p);
    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }
}
