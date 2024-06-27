package com.ecommerce.api.dto.request;

import java.util.List;

import com.ecommerce.api.constant.enums.ProductSize;

import lombok.Data;

@Data
public class CreateProductReqDto {
    String name;
    String description;
    String category;
    List<ProductSize> sizes;
    List<String> colors;
    Integer price;
    Integer totalQty;
    String brand;
}
