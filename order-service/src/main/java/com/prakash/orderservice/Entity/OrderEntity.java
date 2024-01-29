package com.prakash.orderservice.Entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Table (name = "t_orders")
@Transactional
public class OrderEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank (message = "Order Name cannot be blank")
    private String orderName;
    @NotBlank (message = "Order Name cannot be null")
    private String orderNumber;
    @OneToMany (cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemsList;

}
