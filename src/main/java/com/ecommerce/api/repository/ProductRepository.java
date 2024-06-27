package com.ecommerce.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.api.model.Product;
import java.util.List;


public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByName(String name);
    List<Product> findByNameContaining(String name);
}
