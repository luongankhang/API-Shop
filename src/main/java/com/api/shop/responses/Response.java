package com.api.shop.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Response {
    private String status;
    private String message;
    private Object data;

    public static ResponseEntity<Response> responseExceptionNotFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Response("NOT_FOUND", e.getMessage(), null));
    }

    public static ResponseEntity<Response> responseException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Response("INTERNAL_SERVER_ERROR",
                        "There was an error, please try again later.",
                        e.getMessage()));
    }
}
