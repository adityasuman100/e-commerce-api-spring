package com.ecommerce.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.api.model.Brand;
import java.util.List;
import java.util.Optional;


public interface BrandRepository extends MongoRepository<Brand, String> {
    Optional<Brand> findFirstByName(String name);

}
