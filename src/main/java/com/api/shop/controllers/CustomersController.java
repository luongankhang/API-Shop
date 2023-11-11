package com.api.shop.controllers;

import com.api.shop.dtos.CustomersDto;
import com.api.shop.models.Customers;
import com.api.shop.responses.Response;
import com.api.shop.services.CustomersService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/shop/customer")
public class CustomersController {

    @Autowired
    private CustomersService customerService;

    @PostMapping
    public ResponseEntity<Response> addCustomer(@RequestBody CustomersDto customerDTO) {
        try {
            Customers customer = customerService.addCustomer(customerDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response("CREATED",
                            "Customer added successfully.", customer));
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateCustomer(@PathVariable UUID id,
                                                   CustomersDto customerDTO) {
        try {
            Customers updatedCustomer = customerService.updateCustomer(id, customerDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response("OK",
                            "Customer updated successfully.", updatedCustomer));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deletedCustomer(@PathVariable UUID id) {
        try {
            customerService.deleteCustomerById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response("OK",
                            "Customer deleted successfully.", null));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }
}
