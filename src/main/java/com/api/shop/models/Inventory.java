package com.api.shop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tblInventory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// Bảng Kho Hàng (Inventory)
public class Inventory {

    // Mã phụ tùng (Part ID) - Liên kết khóa ngoại với bảng Parts
    @Id
    @OneToOne
    @JoinColumn(name = "partId")
    private Parts part;

    // Số lượng tồn kho (Inventory Quantity)
    @Column(name = "inventoryQuantity")
    private int inventoryQuantity;

    // Vị trí trong kho (Location)
    @Column(name = "location")
    private String location;
}
