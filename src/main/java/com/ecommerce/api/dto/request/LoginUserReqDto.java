package com.ecommerce.api.dto.request;

import lombok.Data;

@Data
public class LoginUserReqDto {
    String email;
    String password;
}
