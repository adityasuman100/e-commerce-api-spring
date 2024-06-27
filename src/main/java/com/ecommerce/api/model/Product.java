package com.ecommerce.api.model;

import com.ecommerce.api.constant.enums.ProductSize;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Data;

@Data
@Document
public class Product {

    @Id
    String _id;
    String name;
    String description;
    String brand;
    String category;
    List<ProductSize> sizes;
    List<String> colors;

    @DocumentReference
    User user;

    List<String> images;

    @DocumentReference
    List<Review> reviews;

    Integer price;

    Integer totalQty;

    Integer totalSold;

    @CreatedDate
    Date createdAt;

    @LastModifiedDate
    Date updatedAt;
}
