package com.api.shop.controllers;

import com.api.shop.dtos.OrdersDto;
import com.api.shop.models.Orders;
import com.api.shop.responses.Response;
import com.api.shop.services.OrdersService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/shop/order")
public class OrdersController {

    @Autowired
    private OrdersService orderService;

    @GetMapping
    public ResponseEntity<Response> getAllOrders() {
        try {
            List<OrdersDto> ordersDtoList = orderService.getAllOrders();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response("OK",
                            "Orders retrieved successfully.", ordersDtoList));
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getOrderById(@PathVariable UUID id) {
        try {
            OrdersDto orderDto = orderService.getOrderById(id);

            return ResponseEntity.ok(new Response("OK",
                    "Order retrieved successfully.", orderDto));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Response> addOrder(@RequestBody OrdersDto orderDto) {
        try {
            Orders order = orderService.addOrder(orderDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response("CREATED",
                            "Order added successfully.", order));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateOrder(@PathVariable UUID id,
                                                @RequestBody OrdersDto orderDto) {
        try {
            Orders updatedOrder = orderService.updateOrder(id, orderDto);
            return ResponseEntity.ok(new Response("OK",
                    "Order updated successfully.", updatedOrder));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteOrder(@PathVariable UUID id) {
        try {
            orderService.deleteOrderById(id);
            return ResponseEntity.ok(new Response("OK",
                    "Order deleted successfully.", null));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }
}
