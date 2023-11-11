package com.api.shop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tblParts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// Bảng Phụ Tùng (Parts)
public class Parts {

    // Mã phụ tùng (Part ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "partId")
    private UUID partId;

    // Tên phụ tùng (Part Name)
    @Column(name = "partName")
    private String partName;

    //Giá (Price)
    @Column(name = "price")
    private String price;

    // Loại phụ tùng (Category ID) - Liên kết khóa ngoại với bảng PartCategories
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private PartCategories partCategory;

    // Mô tả (Description)
    @Column(name = "description")
    private String description;

    // Vị trí trong kho (Location)
    @Column(name = "location")
    private String location;

    // Nhà cung cấp (Supplier ID) - Liên kết khóa ngoại với bảng Suppliers
    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Suppliers supplier;

    // Số lượng tồn kho (Inventory Quantity)
    @Column(name = "inventoryQuantity")
    private int inventoryQuantity;
}
