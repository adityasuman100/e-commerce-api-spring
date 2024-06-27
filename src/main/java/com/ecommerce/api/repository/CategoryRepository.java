package com.ecommerce.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.api.model.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findFirstByName(String name);
}
