package com.api.shop.dtos;

import com.api.shop.models.Customers;
import com.api.shop.models.Parts;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PartReviewsDto {
    private UUID partId;
    private UUID customerId;
    private int rating;
    private String comment;
    private Date reviewDate;
}
