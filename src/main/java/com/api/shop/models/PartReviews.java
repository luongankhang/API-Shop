package com.api.shop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tblPartReviews")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// Bảng Đánh Giá Phụ Tùng (PartReviews)
public class PartReviews {

    // Mã đánh giá (Review ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reviewId")
    private UUID reviewId;

    // Mã phụ tùng (Part ID) - Liên kết khóa ngoại với bảng Parts
    @ManyToOne
    @JoinColumn(name = "partId")
    private Parts part;

    // Mã khách hàng (Customer ID) - Liên kết khóa ngoại với bảng Customers
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customers customer;

    // Đánh giá (Rating)
    @Column(name = "rating")
    private int rating;

    // Bình luận (Comment)
    @Column(name = "comment")
    private String comment;

    // Ngày đánh giá (Review Date)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reviewDate")
    private Date reviewDate;
}
