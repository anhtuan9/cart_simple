package com.cart.cart.repository;

import com.cart.cart.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findAllByname (String name,Pageable pageable);
    Product findByname (String name);
}
