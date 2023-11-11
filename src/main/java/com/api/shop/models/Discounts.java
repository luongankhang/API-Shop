package com.api.shop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tblDiscounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// Bảng Giảm Giá (Discounts)
public class Discounts {

    // Mã giảm giá (Discount ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "discountId")
    private UUID discountId;

    // Mã phụ tùng (Part ID) - Liên kết khóa ngoại với bảng Parts
    @ManyToOne
    @JoinColumn(name = "partId")
    private Parts part;

    // Giá trị giảm giá (Discount Value)
    @Column(name = "discountValue")
    private double discountValue;

    // Ngày bắt đầu (Start Date)
    @Temporal(TemporalType.DATE)
    @Column(name = "startDate")
    private Date startDate;

    // Ngày kết thúc (End Date)
    @Temporal(TemporalType.DATE)
    @Column(name = "endDate")
    private Date endDate;
}
