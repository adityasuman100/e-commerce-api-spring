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
public class Color {

    @Id
    String _id;

    String name;

    @DocumentReference
    User user;

    @CreatedDate
    Date createdAt;

    @LastModifiedDate
    Date updatedAt;
}
