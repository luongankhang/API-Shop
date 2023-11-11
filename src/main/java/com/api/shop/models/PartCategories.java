package com.api.shop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tblPartCategories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// Bảng Loại Phụ Tùng (PartCategories)
public class PartCategories {

    // Mã loại phụ tùng (Category ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "supplierId")
    private UUID categoryId;

    //Tên loại (Category Name)
    @Column(name = "categoryName")
    private String categoryName;
}
