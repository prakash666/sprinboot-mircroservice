package com.prakash.productService.GlobalExceptionHandler;


import com.prakash.productService.Model.ConstraintViolationExceptionModel;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler (DefaultException.class)
    public String DefaultException(DefaultException ex) {
      return ex.getMessage();
    }

    @ExceptionHandler (ConstraintViolationException.class)
    public String ConstraintViolationException
            ( ConstraintViolationExceptionModel ex) {
        return "Bad Request"+ex.getDefaultMessage();
    }



}
