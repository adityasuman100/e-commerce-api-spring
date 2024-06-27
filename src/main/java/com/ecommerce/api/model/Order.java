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
public class Order {
    
    @Id
    String _id;

    @DocumentReference
    User user;

    List<Object> orderItems;

    ShippingAddress shippingAddress;
    String orderNumber;
    String paymentStatus;
    String paymentMethod;
    Integer totalPrice;

    String currency;
    String status;

    Date deliveredAt;

    @CreatedDate
    Date createdAt;

    @LastModifiedDate
    Date updatedAt;
}
