package com.api.shop.controllers;

import com.api.shop.dtos.PartCategoriesDto;
import com.api.shop.models.PartCategories;
import com.api.shop.responses.Response;
import com.api.shop.services.PartCategoriesService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/shop/partcategory")
public class PartCategoriesController {

    @Autowired
    private PartCategoriesService partCategoryService;

    private static final Logger logger = LoggerFactory.getLogger(PartCategoriesController.class);

    @GetMapping
    public ResponseEntity<Response> getAllPartCategories() {
        try {
            List<PartCategories> partCategories = partCategoryService.getAllPartCategories();

            logger.info("Successfully retrieved the list of part categories.");

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response("OK",
                            "Successfully retrieved the list of part categories.",
                            partCategories));
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCategoryById(@PathVariable UUID id) {
        try {
            PartCategories partCategory = partCategoryService.getPartCategoryById(id);

            logger.info("Successfully retrieved the list of part category. Category ID: {}",
                    partCategory.getCategoryId());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response("OK",
                            "Successfully retrieved the list of part category.",
                            partCategory));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Response> addPartCategorie(@RequestBody
                                                     PartCategoriesDto partCategoryDTO) {
        String categoryName = partCategoryDTO.getCategoryName();

        try {
            if (partCategoryService.isCategoryNameExists(categoryName)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response("BAD_REQUEST",
                                "Duplicated category name.", "Null"));
            }

            PartCategories partCategory = partCategoryService
                    .addPartCategory(partCategoryDTO);

            logger.info("Category added successfully. Category ID: {}",
                    partCategory.getCategoryId());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response("CREATED",
                            "Category added successfully.", partCategory));
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateCategory(@PathVariable UUID id,
                                                   @RequestBody
                                                   PartCategoriesDto partCategoryDTO) {
        try {
            PartCategories updatedPartCategory = partCategoryService
                    .updateCategory(id, partCategoryDTO);

            logger.info("Category updated successfully. Category ID: {}",
                    updatedPartCategory.getCategoryId());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response("OK",
                            "Category updated successfully.", updatedPartCategory));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteSupplier(@PathVariable UUID id) {
        try {
            partCategoryService.deleteCategoryById(id);

            logger.info("Category deleted successfully. Category ID: {}", id);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response("OK",
                            "Category deleted successfully.", "Null"));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }
}
