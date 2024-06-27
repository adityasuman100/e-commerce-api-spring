package com.ecommerce.api.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Data;

@Document
@Data
public class Brand {
    @Id
    String _id;

    String name;

    @DocumentReference
    User user;

    @DocumentReference
    List<Product> products;

    @CreatedDate
    Date createdAt;

    @LastModifiedDate
    Date updatedAt;
}
