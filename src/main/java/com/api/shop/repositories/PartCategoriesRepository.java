package com.api.shop.repositories;

import com.api.shop.models.PartCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartCategoriesRepository extends JpaRepository<PartCategories, UUID> {

    boolean existsByCategoryName(String categoryName);
}
