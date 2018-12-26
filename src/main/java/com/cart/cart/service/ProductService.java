package com.cart.cart.service;

import com.cart.cart.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    Product findById(Long id);
    void save(Product product);
    void remove(Long id);
    void setupimg(Product product);
    Page<Product> findAllByName (String name, Pageable pageable);
    Product findByName (String name);
}
