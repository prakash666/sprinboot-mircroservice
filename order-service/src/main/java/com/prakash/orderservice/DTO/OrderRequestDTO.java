package com.prakash.orderservice.DTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {
    private Long id;
    @NotBlank (message = "OrderName cannot be null")
    private String orderName;
    @NotBlank (message = "OrderNumber cannot be null")
     private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderRequestLineItems> orderRequestLineItemsDto;


}
