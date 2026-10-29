package com.purnima.campusconnect.common;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.*;
@RestControllerAdvice
public class ApiExceptionHandler {
    public record ApiError(Instant timestamp,int status,String error,String message,Map<String,String> fieldErrors) {}
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ApiError> notFound(NotFoundException ex) {
        return ResponseEntity.status(404).body(new ApiError(Instant.now(),404,"Not Found",ex.getMessage(),Map.of()));
    }
    @ExceptionHandler({DuplicateResourceException.class, DataIntegrityViolationException.class})
    ResponseEntity<ApiError> conflict(Exception ex) {
        return ResponseEntity.status(409).body(new ApiError(Instant.now(),409,"Conflict",
            ex instanceof DuplicateResourceException ? ex.getMessage() : "A record with the same unique value already exists.",Map.of()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> validation(MethodArgumentNotValidException ex) {
        Map<String,String> fields=new TreeMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> fields.putIfAbsent(e.getField(),e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(new ApiError(Instant.now(),400,"Validation Failed","Please correct the highlighted fields.",fields));
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiError> unexpected(Exception ex) {
        return ResponseEntity.status(500).body(new ApiError(Instant.now(),500,"Internal Server Error","Unexpected server error.",Map.of()));
    }
}