package com.api.shop.controllers;

import com.api.shop.dtos.PartReviewsDto;
import com.api.shop.models.PartReviews;
import com.api.shop.responses.Response;
import com.api.shop.services.PartReviewsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/shop/partreview")
public class PartReviewsController {

    @Autowired
    private PartReviewsService partReviewService;

    @PostMapping
    public ResponseEntity<Response> addPartReview(@RequestBody PartReviewsDto partReviewDTO) {
        try {
            PartReviews partReview = partReviewService.addPartReview(partReviewDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response("CREATED", "Part Review added successfully.", partReview));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updatePartReview(@PathVariable UUID id, @RequestBody PartReviewsDto partReviewDTO) {
        try {
            PartReviews updatedPartReview = partReviewService.updatePartReview(id, partReviewDTO);
            return ResponseEntity.ok(new Response("SUCCESS", "Part Review updated successfully.", updatedPartReview));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deletePartReview(@PathVariable UUID id) {
        try {
            partReviewService.deletePartReview(id);
            return ResponseEntity.ok(new Response("SUCCESS", "Part Review deleted successfully.", null));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }
}
