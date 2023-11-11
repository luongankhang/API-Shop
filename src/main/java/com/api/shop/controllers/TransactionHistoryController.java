package com.api.shop.controllers;

import com.api.shop.dtos.TransactionHistoryDto;
import com.api.shop.models.TransactionHistory;
import com.api.shop.responses.Response;
import com.api.shop.services.TransactionHistoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/shop/transactionhistory")
public class TransactionHistoryController {

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @PostMapping
    public ResponseEntity<Response> addTransactionHistory(@RequestBody
                                                          TransactionHistoryDto
                                                                  transactionHistoryDTO) {
        try {
            TransactionHistory transactionHistory = transactionHistoryService
                    .addTransactionHistory(transactionHistoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response("CREATED",
                            "Transaction History added successfully.",
                            transactionHistory));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateTransactionHistory(@PathVariable UUID id, @RequestBody TransactionHistoryDto transactionHistoryDTO) {
        try {
            TransactionHistory transactionHistory = transactionHistoryService.updateTransactionHistory(id, transactionHistoryDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("OK", "Transaction History updated successfully.", transactionHistory));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteTransactionHistory(@PathVariable UUID id) {
        try {
            transactionHistoryService.deleteTransactionHistoryById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("OK", "Transaction History deleted successfully.", null));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }
}
