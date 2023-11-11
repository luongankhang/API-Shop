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
public class TransactionHistoryDto {
    private UUID orderId;
    private Date transactionDate;
    private String transactionType;
    private double amount;
}
