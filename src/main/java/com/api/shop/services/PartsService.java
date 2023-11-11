package com.api.shop.services;

import com.api.shop.dtos.PartsDto;
import com.api.shop.models.PartCategories;
import com.api.shop.models.Parts;
import com.api.shop.models.Suppliers;
import com.api.shop.repositories.PartCategoriesRepository;
import com.api.shop.repositories.PartsRepository;
import com.api.shop.repositories.SuppliersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PartsService {

    @Autowired
    private PartsRepository partRepository;

    @Autowired
    private PartCategoriesRepository partCategoryRepository;

    @Autowired
    private SuppliersRepository suppliersRepository;

    public List<PartsDto> getAllParts() {
        List<Parts> parts = partRepository.findAll();
        List<PartsDto> partsDtoList = new ArrayList<>();

        for (Parts part : parts) {
            PartsDto partDto = new PartsDto();
            partDto.setPartName(part.getPartName());
            partDto.setPrice(part.getPrice());
            partDto.setDescription(part.getDescription());
            partDto.setLocation(part.getLocation());
            partDto.setInventoryQuantity(part.getInventoryQuantity());
            partDto.setPartCategoryId(part.getPartCategory().getCategoryId());
            partDto.setPartCategoryName(part.getPartCategory().getCategoryName());
            partDto.setSupplierId(part.getSupplier().getSupplierId());
            partDto.setSupplierName(part.getSupplier().getSupplierName());

            partsDtoList.add(partDto);
        }

        return partsDtoList;
    }

    public PartsDto getPartById(UUID id) {
        Parts part = partRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part not found."));
        PartsDto partDto = new PartsDto();

        partDto.setPartName(part.getPartName());
        partDto.setPrice(part.getPrice());
        partDto.setDescription(part.getDescription());
        partDto.setLocation(part.getLocation());
        partDto.setInventoryQuantity(part.getInventoryQuantity());
        partDto.setPartCategoryId(part.getPartCategory().getCategoryId());
        partDto.setPartCategoryName(part.getPartCategory().getCategoryName());
        partDto.setSupplierId(part.getSupplier().getSupplierId());
        partDto.setSupplierName(part.getSupplier().getSupplierName());

        return partDto;
    }

    public Parts addPart(PartsDto partsDTO) {
        Parts part = new Parts();

        part.setPartName(partsDTO.getPartName());
        part.setPrice(partsDTO.getPrice());
        part.setDescription(partsDTO.getDescription());
        part.setLocation(partsDTO.getLocation());
        part.setInventoryQuantity(partsDTO.getInventoryQuantity());

        UUID partCategoryId = partsDTO.getPartCategoryId();
        PartCategories partCategory = partCategoryRepository.findById(partCategoryId)
                .orElseThrow(() -> new EntityNotFoundException("Part Category not found."));
        part.setPartCategory(partCategory);

        UUID supplierId = partsDTO.getSupplierId();
        Suppliers supplier = suppliersRepository.findById(supplierId)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found."));
        part.setSupplier(supplier);

        return partRepository.save(part);
    }

    public Parts updatePart(UUID id, PartsDto partsDTO) {
        Parts existingPart = partRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part not found."));

        existingPart.setPartName(partsDTO.getPartName());
        existingPart.setPrice(partsDTO.getPrice());
        existingPart.setDescription(partsDTO.getDescription());
        existingPart.setLocation(partsDTO.getLocation());
        existingPart.setInventoryQuantity(partsDTO.getInventoryQuantity());

        UUID partCategoryId = partsDTO.getPartCategoryId();
        PartCategories partCategory = partCategoryRepository.findById(partCategoryId)
                .orElseThrow(() -> new EntityNotFoundException("Part Category not found."));
        existingPart.setPartCategory(partCategory);

        UUID supplierId = partsDTO.getSupplierId();
        Suppliers supplier = suppliersRepository.findById(supplierId)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found."));
        existingPart.setSupplier(supplier);

        return partRepository.save(existingPart);
    }

    public void deletePartById(UUID id) {
        Parts existingPart = partRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part not found."));
        partRepository.delete(existingPart);
    }
}
