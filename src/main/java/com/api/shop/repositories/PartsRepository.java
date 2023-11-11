package com.api.shop.repositories;

import com.api.shop.models.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartsRepository extends JpaRepository<Parts, UUID> {
}
