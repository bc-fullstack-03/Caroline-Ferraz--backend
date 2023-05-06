package com.parrot.parrotapi.Infrastructure.ExceptionHandling;

import com.amazonaws.services.glue.model.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity EntityNotFoundHandler(){
        return ResponseEntity.notFound().build();
    }
}
