package com.api.shop.services;

import com.api.shop.dtos.OrderDetailsDto;
import com.api.shop.models.OrderDetails;
import com.api.shop.models.Orders;
import com.api.shop.models.Parts;
import com.api.shop.repositories.OrderDetailsRePository;
import com.api.shop.repositories.OrdersRepository;
import com.api.shop.repositories.PartsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderDetailsService {

    @Autowired
    private OrderDetailsRePository orderDetailRepository;

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private PartsRepository partRepository;

    public OrderDetails addOrderDetail(OrderDetailsDto orderDetailDTO) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setQuantity(orderDetailDTO.getQuantity());
        orderDetails.setPrice(orderDetailDTO.getPrice());

        UUID orderId = orderDetailDTO.getOrderId();
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found."));
        orderDetails.setOrder(order);

        UUID partId = orderDetailDTO.getPartId();
        Parts part = partRepository.findById(partId)
                .orElseThrow(() -> new EntityNotFoundException("Part not found."));
        orderDetails.setPart(part);

        return orderDetailRepository.save(orderDetails);
    }

    public OrderDetails updateOrderDetail(UUID id, OrderDetailsDto orderDetailDTO) {
        OrderDetails orderDetails = orderDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order details not found."));

        orderDetails.setQuantity(orderDetailDTO.getQuantity());
        orderDetails.setPrice(orderDetailDTO.getPrice());

        UUID orderId = orderDetailDTO.getOrderId();
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found."));
        orderDetails.setOrder(order);

        UUID partId = orderDetailDTO.getPartId();
        Parts part = partRepository.findById(partId)
                .orElseThrow(() -> new EntityNotFoundException("Part not found."));
        orderDetails.setPart(part);

        return orderDetailRepository.save(orderDetails);
    }

    public void deleteOrderDetailById(UUID id) {
        OrderDetails orderDetails = orderDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order details not found."));
        orderDetailRepository.delete(orderDetails);
    }
}
