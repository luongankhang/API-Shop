package com.api.shop.controllers;

import com.api.shop.models.Images;
import com.api.shop.repositories.ImagesRepository;
import com.api.shop.responses.Response;
import com.api.shop.services.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/api/shop/fileupload")
public class FileUploadController {

    private final IStorageService storageService;

    @Autowired
    private ImagesRepository repository;

    @Autowired
    public FileUploadController(IStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping
    public ResponseEntity<Response> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                String generatedFileName = storageService.storeFile(file);
                Images image = new Images(generatedFileName);
                repository.save(image);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new Response("Ok", "Upload file successfully", generatedFileName));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.OK).body(new Response("Failed", e.getMessage(), null));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response("Failed", "No file found in the request", null));
        }
    }

    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

}
