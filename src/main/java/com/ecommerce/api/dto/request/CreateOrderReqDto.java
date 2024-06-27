package com.ecommerce.api.dto.request;

import java.util.List;

import com.ecommerce.api.model.ShippingAddress;

import lombok.Data;

@Data
public class CreateOrderReqDto {
    List<Object> orderItems;
    ShippingAddress shippingAddress;
    Integer totalPrice;
}
