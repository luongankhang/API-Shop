package com.api.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiscountsDto {
    private UUID partId;
    private String partName;
    private double discountValue;
    private Date startDate;
    private Date endDate;
}
