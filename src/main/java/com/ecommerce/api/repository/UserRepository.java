package com.ecommerce.api.repository;

import com.ecommerce.api.model.User;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByEmail(String email);
}
