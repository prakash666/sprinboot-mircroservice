package com.prakash.inventoryService.Service;


import com.prakash.inventoryService.DTO.InventoryRequestDto;
import com.prakash.inventoryService.DTO.InventoryResponseDto;
import com.prakash.inventoryService.Entity.InventoryEntity;
import com.prakash.inventoryService.ExceptionHandler.DefaultException;
import com.prakash.inventoryService.Repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService (InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly = true)
    public List<InventoryResponseDto> InventoryServiceData(List<String> skuCode) throws Exception {
        try{
            List<InventoryEntity> inventoryEntities = inventoryRepository.findBySkuCodeIn (skuCode);
            if (inventoryEntities.isEmpty ()) {
                throw new DefaultException ("Cannot find skuCode");
            } else {
                return inventoryRepository.findBySkuCodeIn(skuCode).stream ()
                        .map (inventoryEntity ->
                                InventoryResponseDto.builder ()
                                        .id(inventoryEntity.getId ())
                                        .skuCode (inventoryEntity.getSkuCode ())
                                        .isInStock (inventoryEntity.getQuantity () > 0)
                                        .build ()
                        ).collect(Collectors.toList());
            }
        } catch (Exception e) {
            throw new DefaultException ("No Such SkuCode");
        }

    }

         public void inventoryPost(InventoryRequestDto inventoryRequestDto) throws Exception{
        try{
            InventoryEntity inventoryEntity = InventoryEntity.builder()
                    .id(inventoryRequestDto.getId())
                    .skuCode(inventoryRequestDto.getSkuCode())
                    .quantity(inventoryRequestDto.getQuantity())
                    .build();
            inventoryRepository.save(inventoryEntity);
        } catch (Exception e) {
            throw new DefaultException("Inventory data cannot be posted"+e.getMessage());
        }

    }

    public List<InventoryResponseDto> inventoryGet() throws Exception {
        try{
            List<InventoryEntity> inventoryData = inventoryRepository.findAll();
            return  inventoryData.stream()
                    .map(inventoryDataValues-> mapToInventoryData(inventoryDataValues)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new DefaultException("Cannot return value");
        }

    }

    private InventoryResponseDto mapToInventoryData(InventoryEntity inventoryEntity) {
     return    InventoryResponseDto.builder()
                .id(inventoryEntity.getId())
                .skuCode(inventoryEntity.getSkuCode())
                .quantity(inventoryEntity.getQuantity())
                .build();
    }

}



