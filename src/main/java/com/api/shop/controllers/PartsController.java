package com.api.shop.controllers;

import com.api.shop.dtos.PartsDto;
import com.api.shop.models.Parts;
import com.api.shop.responses.Response;
import com.api.shop.services.PartsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/shop/part")
public class PartsController {

    @Autowired
    private PartsService partService;

    @GetMapping
    public ResponseEntity<Response> getAllParts() {
        try {
            List<PartsDto> partsDtoList = partService.getAllParts();

            return ResponseEntity.ok(new Response("OK",
                    "Parts retrieved successfully.", partsDtoList));
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getPartById(@PathVariable("id") UUID id) {
        try {
            PartsDto partDto = partService.getPartById(id);

            return ResponseEntity.ok(new Response("OK",
                    "Part retrieved successfully.", partDto));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Response> addPart(@RequestBody PartsDto partDTO) {
        try {
            Parts part = partService.addPart(partDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response("CREATED",
                            "Part added successfully.", part));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updatePart(@PathVariable UUID id,
                                               @RequestBody PartsDto partDTO) {
        try {
            Parts updatedPart = partService.updatePart(id, partDTO);
            return ResponseEntity.ok(new Response("OK",
                    "Part updated successfully.", updatedPart));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deletePart(@PathVariable UUID id) {
        try {
            partService.deletePartById(id);
            return ResponseEntity.ok(new Response("OK",
                    "Part deleted successfully.", null));
        } catch (EntityNotFoundException e) {
            return Response.responseExceptionNotFound(e);
        } catch (Exception e) {
            return Response.responseException(e);
        }
    }
}
