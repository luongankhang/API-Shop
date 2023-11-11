package com.api.shop.services;

import com.api.shop.dtos.DiscountsDto;
import com.api.shop.models.Discounts;
import com.api.shop.models.Parts;
import com.api.shop.repositories.DiscountsRepository;
import com.api.shop.repositories.PartsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DiscountsService {

    @Autowired
    private DiscountsRepository discountRepository;

    @Autowired
    private PartsRepository partRepository;

    public List<DiscountsDto> getAllDiscounts() {
        List<Discounts> discounts = discountRepository.findAll();
        List<DiscountsDto> discountsDtoList = new ArrayList<>();

        for (Discounts discount : discounts) {
            DiscountsDto discountDto = new DiscountsDto();
            discountDto.setPartId(discount.getPart().getPartId());
            discountDto.setPartName(discount.getPart().getPartName());
            discountDto.setDiscountValue(discount.getDiscountValue());
            discountDto.setStartDate(discount.getStartDate());
            discountDto.setEndDate(discount.getEndDate());

            discountsDtoList.add(discountDto);
        }

        return discountsDtoList;
    }

    public DiscountsDto getDiscountById(UUID id) {
        Discounts discount = discountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discount not found."));
        DiscountsDto discountDto = new DiscountsDto();

        discountDto.setPartId(discount.getPart().getPartId());
        discountDto.setPartName(discount.getPart().getPartName());
        discountDto.setDiscountValue(discount.getDiscountValue());
        discountDto.setStartDate(discount.getStartDate());
        discountDto.setEndDate(discount.getEndDate());

        return discountDto;
    }

    public Discounts addDiscount(DiscountsDto discountDTO) {
        Discounts discount = new Discounts();

        discount.setDiscountValue(discountDTO.getDiscountValue());
        discount.setStartDate(discountDTO.getStartDate());
        discount.setEndDate(discountDTO.getEndDate());

        UUID partId = discountDTO.getPartId();
        Parts part = partRepository.findById(partId)
                .orElseThrow(() -> new EntityNotFoundException("Part not found."));
        discount.setPart(part);

        return discountRepository.save(discount);
    }

    public Discounts updateDiscount(UUID id, DiscountsDto discountDTO) {
        Discounts existingDiscount = discountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discount not found."));

        existingDiscount.setDiscountValue(discountDTO.getDiscountValue());
        existingDiscount.setStartDate(discountDTO.getStartDate());
        existingDiscount.setEndDate(discountDTO.getEndDate());

        UUID partId = discountDTO.getPartId();
        Parts part = partRepository.findById(partId)
                .orElseThrow(() -> new EntityNotFoundException("Part not found."));
        existingDiscount.setPart(part);

        return discountRepository.save(existingDiscount);
    }

    public void deleteDiscountById(UUID id) {
        Discounts existingDiscount = discountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discount not found."));
        discountRepository.delete(existingDiscount);
    }
}
