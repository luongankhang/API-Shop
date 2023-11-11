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
public class PartsDto {
    private String partName;
    private String price;
    private UUID partCategoryId;
    private String partCategoryName;
    private String description;
    private String location;
    private UUID supplierId;
    private String supplierName;
    private int inventoryQuantity;
}
