package com.ecommerce.api.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Data;

@Document
@Data
public class Review {
    
    @Id
    String _id;

    @DocumentReference
    User user;

    @DocumentReference
    Product product;

    String message;

    Integer rating;

    @CreatedDate
    Date createdAt;

    @LastModifiedDate
    Date updatedAt;
}
