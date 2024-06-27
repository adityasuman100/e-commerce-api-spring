package com.ecommerce.api.dto.request;

import lombok.Data;

@Data
public class UpdateShippingAddressReqDto {
    String firstName;
    String lastName;
    String address;
    String city;
    String postalCode;
    String province;
    String phone;
    String country;
}
