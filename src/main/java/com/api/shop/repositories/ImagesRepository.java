package com.api.shop.repositories;

import com.api.shop.models.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImagesRepository extends JpaRepository<Images, UUID> {
}
