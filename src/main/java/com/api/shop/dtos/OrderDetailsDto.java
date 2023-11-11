package com.api.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailsDto {
    private UUID orderId;
    private UUID partId;
    private int quantity;
    private String price;
}
