package com.prakash.orderservice.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponseDto {
    private Long id;
    @NotBlank(message = "Skucode cannot be null")
    private String skuCode;
    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;
    private boolean isInStock;

}
