package com.prakash.orderservice.Service;


import com.prakash.orderservice.DTO.InventoryResponseDto;
import com.prakash.orderservice.DTO.OrderRequestDTO;
import com.prakash.orderservice.DTO.OrderRequestLineItems;
import com.prakash.orderservice.DTO.OrderResponse;
import com.prakash.orderservice.Entity.OrderEntity;
import com.prakash.orderservice.Entity.OrderLineItems;
import com.prakash.orderservice.GlobalExceptionHandler.DefaultException;
import com.prakash.orderservice.Repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    private final WebClient webClient;

    public OrderService(OrderRepository orderRepository, WebClient webClient) {
        this.orderRepository = orderRepository;
        this.webClient = webClient;
    }


    public void placeOrders(OrderRequestDTO orderRequestDTO) throws Exception {
        try{
           OrderEntity orderEntity = new OrderEntity();
            orderEntity.setId(orderRequestDTO.getId());
            orderEntity.setOrderName (orderRequestDTO.getOrderName ());
            orderEntity.setOrderNumber(UUID.randomUUID().toString());
            //orderEntity.setOrderNumber(orderRequest.getOrderName());
            List<OrderLineItems>  orderItems = orderRequestDTO.getOrderRequestLineItemsDto() .stream().map(orderRequestMethod ->
                    mapToDto(orderRequestMethod)).collect(Collectors.toList());
            orderEntity.setOrderLineItemsList(orderItems);

          List<String> skuCodes =   orderEntity.getOrderLineItemsList ().stream ()
                    .map (orderLineItems -> orderLineItems.getSkuCode ()).collect(Collectors.toList());
            InventoryResponseDto [] inventoryResponseDtos =     webClient.get ()
                    .uri ("http://localhost:1010/api/inventory/code",uriBuilder -> uriBuilder.
                            queryParam ("skuCode",skuCodes).build ())
                            .retrieve ()// This is used to get  or retrieve information from specified API
                                    .bodyToMono (InventoryResponseDto [].class)   // it is used to read data from web-client response we need to add body to mono method.
                    .block (); // It is used to make a Synchronous call we need to call an block(); to make Synchronous call
                    boolean allProductInStock = Arrays.stream (inventoryResponseDtos)
                    .allMatch (inventoryResponseDto -> inventoryResponseDto.isInStock ());

                if (Boolean.TRUE.equals (allProductInStock)) {
                    orderRepository.save (orderEntity);
                }
            // Call Inventory service and place order if product is in Stock
        } catch (Exception e) {
            throw new DefaultException ("No Such SkuCode found" +e.getMessage ());
        }

    }


    private OrderLineItems mapToDto(OrderRequestLineItems orderRequestLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        if (orderRequestLineItemsDto.getPrice() != null) {
            orderLineItems.setPrice(orderRequestLineItemsDto.getPrice());
        }
        if (orderRequestLineItemsDto.getSkuCode() != null){
            orderLineItems.setSkuCode(orderRequestLineItemsDto.getSkuCode());
        }
        if (orderRequestLineItemsDto.getQuantity() != null) {
            orderLineItems.setQuantity(orderRequestLineItemsDto.getQuantity());
        }
        return orderLineItems;
    }


    
    public List<OrderResponse> getOrder() throws Exception{
        try{
            List<com.prakash.orderservice.Entity.OrderEntity> orderEntities = orderRepository.findAll();
            return orderEntities.stream().map(orders->mapOrderResposes(orders)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("No Orders to return"+e.getMessage());
        }

    }
    private OrderResponse mapOrderResposes(com.prakash.orderservice.Entity.OrderEntity orderEntity)  {
            return OrderResponse.builder()
                    .id(orderEntity.getId())
                    .orderName(orderEntity.getOrderNumber())
                    .orderLineIteamsList(orderEntity.getOrderLineItemsList())
                    .build();
    }
}
