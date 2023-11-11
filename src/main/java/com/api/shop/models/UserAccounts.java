package com.api.shop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tblUserAccounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// Bảng Tài Khoản (UserAccounts)
public class UserAccounts {

    // Mã tài khoản (Account ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "accountId")
    private UUID accountId;

    // Tên người dùng (Username)
    @Column(name = "username")
    private String username;

    // Mật khẩu (Password)
    @Column(name = "password")
    private String password;

    // Quyền truy cập (Role)
    @Column(name = "role")
    private String role;
}
