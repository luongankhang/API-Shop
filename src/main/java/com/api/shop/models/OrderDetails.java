package com.api.shop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tblOrderDetails")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// Bảng Chi Tiết Đơn Hàng (OrderDetails)
public class OrderDetails {

    // Mã chi tiết đơn hàng (Order Detail ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderDetailId")
    private UUID orderDetailId;

    // Mã đơn hàng (Order ID) - Liên kết khóa ngoại với bảng Orders
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders order;

    // Mã phụ tùng (Part ID) - Liên kết khóa ngoại với bảng Parts
    @ManyToOne
    @JoinColumn(name = "partId")
    private Parts part;

    // Số lượng (Quantity)
    @Column(name = "quantity")
    private int quantity;

    // Giá (Price)
    @Column(name = "price")
    private String price;
}
