package com.api.shop.services;

import com.api.shop.dtos.TransactionHistoryDto;
import com.api.shop.models.Orders;
import com.api.shop.models.TransactionHistory;
import com.api.shop.repositories.OrdersRepository;
import com.api.shop.repositories.TransactionHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionHistoryService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    private OrdersRepository orderRepository;

    public TransactionHistory addTransactionHistory(TransactionHistoryDto transactionHistoryDTO) {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setTransactionDate(transactionHistoryDTO.getTransactionDate());
        transactionHistory.setTransactionType(transactionHistoryDTO.getTransactionType());
        transactionHistory.setAmount(transactionHistoryDTO.getAmount());

        UUID orderId = transactionHistoryDTO.getOrderId();
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found."));
        transactionHistory.setOrder(order);

        return transactionHistoryRepository.save(transactionHistory);
    }

    public TransactionHistory updateTransactionHistory(UUID id, TransactionHistoryDto transactionHistoryDTO) {
        TransactionHistory transactionHistory = transactionHistoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction history not found."));

        transactionHistory.setTransactionDate(transactionHistoryDTO.getTransactionDate());
        transactionHistory.setTransactionType(transactionHistoryDTO.getTransactionType());
        transactionHistory.setAmount(transactionHistoryDTO.getAmount());

        UUID orderId = transactionHistoryDTO.getOrderId();
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found."));
        transactionHistory.setOrder(order);

        return transactionHistoryRepository.save(transactionHistory);
    }

    public void deleteTransactionHistoryById(UUID id) {
        TransactionHistory transactionHistory = transactionHistoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction history not found."));
        transactionHistoryRepository.delete(transactionHistory);
    }
}
