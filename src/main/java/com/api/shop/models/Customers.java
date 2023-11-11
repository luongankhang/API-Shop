package com.api.shop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tblCustomers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//Bảng Khách Hàng (Customers)
public class Customers {

    // Mã khách hàng (Customer ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerId")
    private UUID customerId;

    // Tên (Name)
    @Column(name = "name")
    private String name;

    // Địa chỉ (Address)
    @Column(name = "address")
    private String address;

    // Số điện thoại (Phone)
    @Column(name = "phone")
    private String phone;

    // Email
    @Column(name = "email")
    private String email;
}
