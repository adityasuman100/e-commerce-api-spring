package com.ecommerce.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.api.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
    
}
