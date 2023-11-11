package com.api.shop.services;

import com.api.shop.dtos.PartCategoriesDto;
import com.api.shop.models.PartCategories;
import com.api.shop.repositories.PartCategoriesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PartCategoriesService {

    @Autowired
    private PartCategoriesRepository partCategoryRepository;

    public List<PartCategories> getAllPartCategories() {
        return partCategoryRepository.findAll();
    }

    public PartCategories getPartCategoryById(UUID id) {
        return partCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part category not found."));
    }

    public boolean isCategoryNameExists(String categoryName) {
        return partCategoryRepository.existsByCategoryName(categoryName);
    }

    public PartCategories addPartCategory(PartCategoriesDto partCategoryDTO) {
        PartCategories partCategory = new PartCategories();
        partCategory.setCategoryName(partCategoryDTO.getCategoryName());
        return partCategoryRepository.save(partCategory);
    }

    public PartCategories updateCategory(UUID id, PartCategoriesDto partCategoryDTO) {
        PartCategories existingCategory = partCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part category not found."));
        existingCategory.setCategoryName(partCategoryDTO.getCategoryName());
        return partCategoryRepository.save(existingCategory);
    }

    public void deleteCategoryById(UUID id) {
        PartCategories existingCategory = partCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part category not found."));
        partCategoryRepository.delete(existingCategory);
    }
}
