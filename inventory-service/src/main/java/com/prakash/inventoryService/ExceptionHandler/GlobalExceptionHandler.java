package com.prakash.inventoryService.ExceptionHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler (DefaultException.class)
    public String DefaultException (DefaultException ex) {
        return ex.getMessage();
    }
}
