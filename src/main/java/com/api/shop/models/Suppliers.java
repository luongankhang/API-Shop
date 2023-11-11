package com.api.shop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tblSuppliers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// Bảng Nhà Cung Cấp
public class Suppliers {

    // Mã nhà cung cấp (Supplier Id)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "supplierId")
    private UUID supplierId;

    // Tên nhà cung cấp (Supplier Name)
    @Column(name = "supplierName")
    private String supplierName;

    // Địa chỉ (Address)
    @Column(name = "address")
    private String address;

    // Số điện thoại (Phone)
    @Column(name = "phone")
    private String phone;

    // Email
    @Column(name = "email")
    private String email;

    // Website
    @Column(name = "website")
    private String website;
}
