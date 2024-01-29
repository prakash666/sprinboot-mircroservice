package com.prakash.orderservice.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestLineItems {

    private Long id;
    @NotBlank(message = "skuCode cannot be null")
    private String skuCode;
   @Min(value = 0, message = "price cannot be less than Zero")
    private BigDecimal price;
    @Min(value = 0, message = "Quantity cannot be less than Zero")
    private Integer quantity;

}
