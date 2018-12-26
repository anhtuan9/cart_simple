package com.cart.cart.service.impl;

import com.cart.cart.model.Product;
import com.cart.cart.repository.ProductRepository;
import com.cart.cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void setupimg(Product product) {

    }

    @Override
    public Page<Product> findAllByName(String name, Pageable pageable) {
        return productRepository.findAllByname(name,pageable);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByname(name);
    }
}
