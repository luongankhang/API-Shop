package com.api.shop.controllers;

import com.api.shop.dtos.OrderDetailsDto;
import com.api.shop.models.OrderDetails;
import com.api.shop.responses.Response;
import com.api.shop.services.OrderDetailsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/shop/orderdetail")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailService;

    @PostMapping
    public ResponseEntity<Response> addOrderDetail(@RequestBody OrderDetailsDto orderDetailDTO) {
        try {
            OrderDetails orderDetail = orderDetailService.addOrderDetail(orderDetailDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response("CREATED",
                            "OrderDetail added successfully.", orderDetail));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateOrderDetail(@PathVariable("id") UUID id, @RequestBody OrderDetailsDto orderDetailDTO) {
        try {
            OrderDetails orderDetail = orderDetailService.updateOrderDetail(id, orderDetailDTO);
            if (orderDetail != null) {
                return ResponseEntity.ok().body(new Response("SUCCESS",
                        "OrderDetail updated successfully.", orderDetail));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("ERROR",
                        "OrderDetail not found.", null));
            }
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteOrderDetail(@PathVariable("id") UUID id) {
        try {
            orderDetailService.deleteOrderDetailById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }
}
