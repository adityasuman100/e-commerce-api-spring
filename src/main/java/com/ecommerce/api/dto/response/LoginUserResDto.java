package com.ecommerce.api.dto.response;

import com.ecommerce.api.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserResDto {
    User user;
    String token;
}
