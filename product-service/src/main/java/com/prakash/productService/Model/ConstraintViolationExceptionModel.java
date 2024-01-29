package com.prakash.productService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConstraintViolationExceptionModel {
    private String errorMessage;
    private String defaultMessage;

}
