package com.hoangit3.handler;

import com.hoangit3.exception.ResourceNotFoundException;
import com.hoangit3.payload.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException e,
                                                       WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("Resource not found", details);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
