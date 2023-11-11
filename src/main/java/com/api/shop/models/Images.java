package com.api.shop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tblImages")
@NoArgsConstructor
@Getter
@Setter
//Bảng Hình Ảnh (Images)
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "imageId")
    private UUID imageId;

    @Column(name = "fileName")
    private String fileName;

    public Images(String fileName) {
        this.fileName = fileName;
    }
}
