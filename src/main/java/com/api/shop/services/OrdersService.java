package com.api.shop.services;

import com.api.shop.dtos.OrdersDto;
import com.api.shop.models.Customers;
import com.api.shop.models.Orders;
import com.api.shop.repositories.CustomersRepository;
import com.api.shop.repositories.OrdersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private CustomersRepository customerRepository;

    public List<OrdersDto> getAllOrders() {
        List<Orders> orders = orderRepository.findAll();
        List<OrdersDto> ordersDtoList = new ArrayList<>();

        for (Orders order : orders) {
            OrdersDto orderDto = new OrdersDto();
            orderDto.setOrderDate(order.getOrderDate());
            orderDto.setExpectedDeliveryDate(order.getExpectedDeliveryDate());
            orderDto.setCustomerId(order.getCustomer().getCustomerId());
            orderDto.setCustomerName(order.getCustomer().getName());
            orderDto.setTotalAmount(order.getTotalAmount());

            ordersDtoList.add(orderDto);
        }

        return ordersDtoList;
    }

    public OrdersDto getOrderById(UUID id) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found."));
        OrdersDto orderDto = new OrdersDto();

        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setExpectedDeliveryDate(order.getExpectedDeliveryDate());
        orderDto.setCustomerId(order.getCustomer().getCustomerId());
        orderDto.setCustomerName(order.getCustomer().getName());
        orderDto.setTotalAmount(order.getTotalAmount());

        return orderDto;
    }

    public Orders addOrder(OrdersDto orderDto) {
        Orders order = new Orders();

        order.setOrderDate(orderDto.getOrderDate());
        order.setExpectedDeliveryDate(orderDto.getExpectedDeliveryDate());
        order.setTotalAmount(orderDto.getTotalAmount());

        UUID customerId = orderDto.getCustomerId();
        Customers customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found."));
        order.setCustomer(customer);

        return orderRepository.save(order);
    }

    public Orders updateOrder(UUID id, OrdersDto orderDto) {
        Orders existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found."));

        existingOrder.setOrderDate(orderDto.getOrderDate());
        existingOrder.setExpectedDeliveryDate(orderDto.getExpectedDeliveryDate());
        existingOrder.setTotalAmount(orderDto.getTotalAmount());

        UUID customerId = orderDto.getCustomerId();
        Customers customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found."));
        existingOrder.setCustomer(customer);

        return orderRepository.save(existingOrder);
    }

    public void deleteOrderById(UUID id) {
        Orders existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found."));
        orderRepository.delete(existingOrder);
    }
}
