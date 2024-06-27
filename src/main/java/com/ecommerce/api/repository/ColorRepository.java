package com.ecommerce.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.api.model.Color;
import java.util.List;


public interface ColorRepository extends MongoRepository<Color, String> {
    List<Color> findByName(String name);
}
