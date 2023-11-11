package com.api.shop.repositories;

import com.api.shop.models.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, UUID> {

    boolean existsBySupplierName(String supplierName);
}
