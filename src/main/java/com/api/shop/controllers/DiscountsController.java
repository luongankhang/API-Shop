package com.api.shop.controllers;

import com.api.shop.dtos.DiscountsDto;
import com.api.shop.models.Discounts;
import com.api.shop.responses.Response;
import com.api.shop.services.DiscountsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/shop/discount")
public class DiscountsController {

    @Autowired
    private DiscountsService discountService;

    @GetMapping
    public ResponseEntity<Response> getAllDiscounts() {
        try {
            List<DiscountsDto> discountsDtoList = discountService.getAllDiscounts();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response("OK",
                            "Discounts retrieved successfully.", discountsDtoList));
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getDiscountById(@PathVariable("id") UUID id) {
        try {
            DiscountsDto discountDto = discountService.getDiscountById(id);

            return ResponseEntity.ok(new Response("OK",
                    "Discount retrieved successfully.", discountDto));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Response> addDiscount(@RequestBody DiscountsDto discountDTO) {
        try {
            Discounts discount = discountService.addDiscount(discountDTO);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response("CREATED",
                            "Discount added successfully.", discount));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateDiscount(@PathVariable UUID id,
                                                   @RequestBody DiscountsDto discountDTO) {
        try {
            Discounts discount = discountService.updateDiscount(id, discountDTO);
            return ResponseEntity.ok(new Response("OK",
                    "Discount updated successfully.", discount));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteDiscount(@PathVariable UUID id) {
        try {
            discountService.deleteDiscountById(id);
            return ResponseEntity.ok(new Response("OK",
                    "Discount deleted successfully.", null));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }
}
