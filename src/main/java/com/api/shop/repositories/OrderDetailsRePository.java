package com.api.shop.repositories;

import com.api.shop.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderDetailsRePository extends JpaRepository<OrderDetails, UUID> {
}
