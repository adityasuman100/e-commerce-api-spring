package com.ecommerce.api.model;

import lombok.Data;

@Data
public class ShippingAddress {
    String firstName;
    String lastName;
    String address;
    String city;
    String postalCode;
    String province;
    String phone;
    String country;
}
