package com.api.shop.services;


import com.api.shop.dtos.PartReviewsDto;
import com.api.shop.models.Customers;
import com.api.shop.models.PartReviews;
import com.api.shop.models.Parts;
import com.api.shop.repositories.CustomersRepository;
import com.api.shop.repositories.PartReviewsRepository;
import com.api.shop.repositories.PartsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PartReviewsService {

    @Autowired
    private PartReviewsRepository partReviewRepository;

    @Autowired
    private PartsRepository partRepository;

    @Autowired
    private CustomersRepository customerRepository;

    public PartReviews addPartReview(PartReviewsDto partReviewDTO) {
        UUID partId = partReviewDTO.getPartId();
        Parts part = partRepository.findById(partId)
                .orElseThrow(() -> new EntityNotFoundException("Part not found."));

        UUID customerId = partReviewDTO.getCustomerId();
        Customers customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found."));

        PartReviews partReview = new PartReviews();
        partReview.setPart(part);
        partReview.setCustomer(customer);
        partReview.setRating(partReviewDTO.getRating());
        partReview.setComment(partReviewDTO.getComment());
        partReview.setReviewDate(partReviewDTO.getReviewDate());

        return partReviewRepository.save(partReview);
    }

    public PartReviews updatePartReview(UUID id, PartReviewsDto partReviewDTO) {
        PartReviews existingPartReview = partReviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part Review not found."));

        UUID partId = partReviewDTO.getPartId();
        Parts part = partRepository.findById(partId)
                .orElseThrow(() -> new EntityNotFoundException("Part not found."));

        UUID customerId = partReviewDTO.getCustomerId();
        Customers customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found."));

        existingPartReview.setPart(part);
        existingPartReview.setCustomer(customer);
        existingPartReview.setRating(partReviewDTO.getRating());
        existingPartReview.setComment(partReviewDTO.getComment());
        existingPartReview.setReviewDate(partReviewDTO.getReviewDate());

        return partReviewRepository.save(existingPartReview);
    }

    public void deletePartReview(UUID id) {
        PartReviews partReview = partReviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part Review not found."));

        partReviewRepository.delete(partReview);
    }
}
