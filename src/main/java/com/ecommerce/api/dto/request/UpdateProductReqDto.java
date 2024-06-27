package com.ecommerce.api.dto.request;

import java.util.List;

import com.ecommerce.api.constant.enums.ProductSize;
import com.ecommerce.api.model.User;

import lombok.Data;

@Data
public class UpdateProductReqDto {
    String name;
    String description;
    String brand;
    String category;
    List<ProductSize> sizes;
    List<String> colors;

    User user;

    Integer price;

    Integer totalQty;
}
