package com.kairav.hotelapi.controller;

import com.kairav.hotelapi.dto.ApiError;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class) public ResponseEntity <ApiError>handle(RuntimeException e) {
        return ResponseEntity.badRequest().body(new ApiError(e.getMessage()));
    }
}
