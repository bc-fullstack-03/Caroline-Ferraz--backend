package com.parrot.parrotapi.Infrastructure.ExceptionHandling;

import com.amazonaws.services.glue.model.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFoundHandler(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validationErrorHandler(MethodArgumentNotValidException ex){
        var fieldErrors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(fieldErrors.stream().map(FieldErrorsResponse::new).toList());
    }

    private record FieldErrorsResponse(String field, String message){
        public FieldErrorsResponse(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
