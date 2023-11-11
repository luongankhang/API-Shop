package com.api.shop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tblTransactionHistory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// Bảng Lịch Sử Giao Dịch (TransactionHistory)
public class TransactionHistory {

    // Mã giao dịch (Transaction ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transactionId")
    private UUID transactionId;

    // Mã đơn hàng (Order ID) - Liên kết khóa ngoại với bảng Orders
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders order;

    // Ngày giao dịch (Transaction Date)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "transactionDate")
    private Date transactionDate;

    // Loại giao dịch (Transaction Type)
    @Column(name = "transactionType")
    private String transactionType;

    // Số tiền (Amount)
    @Column(name = "amount")
    private double amount;
}
