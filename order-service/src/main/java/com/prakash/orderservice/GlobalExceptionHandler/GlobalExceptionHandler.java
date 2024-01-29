package com.prakash.orderservice.GlobalExceptionHandler;

import com.prakash.orderservice.Model.OrderModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<List<OrderModel>> handleDefaultException(DefaultException ex) {
            List<OrderModel> orderModels = new ArrayList<>();
            orderModels.add(new OrderModel(ex.getMessage()));
            return new ResponseEntity<>(orderModels, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
