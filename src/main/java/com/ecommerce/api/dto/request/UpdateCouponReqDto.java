package com.ecommerce.api.dto.request;

import java.util.Date;

import lombok.Data;

@Data
public class UpdateCouponReqDto {
    String code;
    Date startDate;
    Date endDate;
    Integer discount;
}
