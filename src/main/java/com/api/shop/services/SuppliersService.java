package com.api.shop.services;

import com.api.shop.dtos.SuppliersDto;
import com.api.shop.models.Suppliers;
import com.api.shop.repositories.SuppliersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SuppliersService {

    @Autowired
    private SuppliersRepository suppliersRepository;

    public List<Suppliers> getAllSuppliers() {
        return suppliersRepository.findAll();
    }

    public Suppliers getSupplierById(UUID id) {
        return suppliersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found."));
    }

    public boolean isSupplierNameExists(String supplierName) {
        return suppliersRepository.existsBySupplierName(supplierName);
    }

    public Suppliers addSupplier(SuppliersDto supplierDTO) {
        Suppliers supplier = new Suppliers();
        supplier.setSupplierName(supplierDTO.getSupplierName());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setPhone(supplierDTO.getPhone());
        supplier.setEmail(supplierDTO.getEmail());
        supplier.setWebsite(supplierDTO.getWebsite());
        return suppliersRepository.save(supplier);
    }

    public Suppliers updateSupplier(UUID id, SuppliersDto supplierDTO) {
        Suppliers existingSupplier = suppliersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found."));
        existingSupplier.setSupplierName(supplierDTO.getSupplierName());
        existingSupplier.setAddress(supplierDTO.getAddress());
        existingSupplier.setPhone(supplierDTO.getPhone());
        existingSupplier.setEmail(supplierDTO.getEmail());
        existingSupplier.setWebsite(supplierDTO.getWebsite());
        return suppliersRepository.save(existingSupplier);
    }

    public void deleteSupplierById(UUID id) {
        Suppliers existingSupplier = suppliersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found."));
        suppliersRepository.delete(existingSupplier);
    }
}
