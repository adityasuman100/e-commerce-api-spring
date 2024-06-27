package com.ecommerce.api.dto.response;

import lombok.Data;

@Data
public class UpdateShippingAddressResDto {
    String firstName;
    String lastName;
    String address;
    String city;
    String postalCode;
    String province;
    String phone;
    String country;
}
