package com.prakash.orderservice.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "t_order_line_items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class OrderLineItems {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank (message = "skuCode cannot be null")
    private String skuCode;
    @Min(value = 0, message = "price cannot be less than Zero")
    private BigDecimal price;
    @Min(value = 0, message = "Quantity cannot be less than Zero")
    private Integer quantity;




}
