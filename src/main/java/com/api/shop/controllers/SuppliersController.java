package com.api.shop.controllers;

import com.api.shop.dtos.SuppliersDto;
import com.api.shop.models.Suppliers;
import com.api.shop.responses.Response;
import com.api.shop.services.SuppliersService;
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
@RequestMapping(path = "/api/shop/supplier")
public class SuppliersController {

    @Autowired
    private SuppliersService supplierService;

    private static final Logger logger = LoggerFactory.getLogger(SuppliersController.class);

    @GetMapping
    public ResponseEntity<Response> getAllSuppliers() {
        try {
            List<Suppliers> suppliers = supplierService.getAllSuppliers();

            logger.info("Successfully retrieved the list of suppliers.");

            return ResponseEntity.status(HttpStatus.OK).body(new Response("OK", "Successfully retrieved the list of suppliers.", suppliers));
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getSupplierById(@PathVariable UUID id) {
        try {
            Suppliers supplier = supplierService.getSupplierById(id);

            logger.info("Successfully retrieved the list of suppliers. Supplier ID: {}", supplier.getSupplierId());

            return ResponseEntity.status(HttpStatus.OK).body(new Response("OK", "Successfully retrieved the list of suppliers.", supplier));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Response> addSupplier(@RequestBody SuppliersDto supplierDTO) {
        String supplierName = supplierDTO.getSupplierName();

        try {
            if (supplierService.isSupplierNameExists(supplierName)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("BAD_REQUEST", "Duplicated supplier name.", "Null"));
            }

            Suppliers supplier = supplierService.addSupplier(supplierDTO);

            logger.info("Supplier added successfully. Supplier ID: {}", supplier.getSupplierId());

            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("CREATED", "Supplier added successfully.", supplier));
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateSupplier(@PathVariable UUID id, @RequestBody SuppliersDto supplierDTO) {
        try {
            Suppliers updatedSupplier = supplierService.updateSupplier(id, supplierDTO);

            logger.info("Supplier updated successfully. Supplier ID: {}", updatedSupplier.getSupplierId());

            return ResponseEntity.status(HttpStatus.OK).body(new Response("OK", "Supplier updated successfully.", updatedSupplier));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteSupplier(@PathVariable UUID id) {
        try {
            supplierService.deleteSupplierById(id);

            logger.info("Supplier deleted successfully. Supplier ID: {}", id);

            return ResponseEntity.status(HttpStatus.OK).body(new Response("OK", "Supplier deleted successfully.", "Null"));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }
}
