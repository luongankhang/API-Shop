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
public class OrdersDto {
    private Date orderDate;
    private Date expectedDeliveryDate;
    private UUID customerId;
    private String customerName;
    private double totalAmount;
}
