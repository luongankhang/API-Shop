package com.api.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SuppliersDto {
    private String supplierName;
    private String address;
    private String phone;
    private String email;
    private String website;
}
