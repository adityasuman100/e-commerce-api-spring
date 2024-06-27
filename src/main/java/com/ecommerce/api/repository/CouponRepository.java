package com.ecommerce.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.api.model.Coupon;
import java.util.List;


public interface CouponRepository extends MongoRepository<Coupon, String> {
    List<Coupon> findByCode(String code);
}
