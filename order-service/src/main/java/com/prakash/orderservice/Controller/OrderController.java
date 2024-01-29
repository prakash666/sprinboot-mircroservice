package com.prakash.orderservice.Controller;

import com.prakash.orderservice.DTO.OrderRequestDTO;
import com.prakash.orderservice.DTO.OrderResponse;
import com.prakash.orderservice.Service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/order")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }



    @PostMapping ("/place")
    public String placeOrder (@RequestBody @Valid OrderRequestDTO orderRequestDTO) throws Exception {
        orderService.placeOrders(orderRequestDTO);
        return "Order placed successfully";
    }

    @GetMapping("/getOrder")
    public List<OrderResponse> getOrder() throws Exception {
        return orderService.getOrder();
    }



}
