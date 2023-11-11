package com.api.shop.services;

import com.api.shop.dtos.CustomersDto;
import com.api.shop.models.Customers;
import com.api.shop.repositories.CustomersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepository customerRepository;

//    public CustomersDto getAllCustomers() {
//
//    }

    public Customers addCustomer(CustomersDto customerDTO) {
        Customers customer = new Customers();
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customer.getEmail());
        return customerRepository.save(customer);
    }

    public Customers updateCustomer(UUID id, CustomersDto customerDTO) {
        Customers existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found."));
        existingCustomer.setName(customerDTO.getName());
        existingCustomer.setAddress(customerDTO.getAddress());
        existingCustomer.setPhone(customerDTO.getAddress());
        existingCustomer.setEmail(customerDTO.getEmail());
        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomerById(UUID id) {
        Customers existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found."));
        customerRepository.delete(existingCustomer);
    }
}
