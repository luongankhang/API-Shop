package com.api.shop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tblOrders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//Bảng Đơn Hàng (Orders)
public class Orders {

    // Mã đơn hàng (Order ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId")
    private UUID orderId;

    // Ngày đặt hàng (Order Date)
    @Temporal(TemporalType.DATE)
    @Column(name = "orderDate")
    private Date orderDate;

    // Ngày giao hàng dự kiến (Expected Delivery Date)
    @Temporal(TemporalType.DATE)
    @Column(name = "expectedDeliveryDate")
    private Date expectedDeliveryDate;

    // Mã khách hàng (Customer ID) - Liên kết khóa ngoại với bảng Customers
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customers customer;

    // Tổng tiền (Total Amount)
    @Column(name = "totalAmount")
    private double totalAmount;
}
