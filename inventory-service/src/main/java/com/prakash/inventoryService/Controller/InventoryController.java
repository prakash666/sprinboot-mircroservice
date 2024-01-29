package com.prakash.inventoryService.Controller;

import com.prakash.inventoryService.DTO.InventoryRequestDto;
import com.prakash.inventoryService.DTO.InventoryResponseDto;
import com.prakash.inventoryService.ExceptionHandler.DefaultException;
import com.prakash.inventoryService.Service.InventoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController (InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


     @PostMapping("/postInventory")
    @ResponseStatus (HttpStatus.CREATED)
    public String inventoryPost (@RequestBody @Valid InventoryRequestDto inventoryRequestDto) throws Exception {
        try{
            inventoryService.inventoryPost(inventoryRequestDto);
            return "Inventory has been posted";
        } catch (Exception e) {
            throw new DefaultException("Inventory not posted");
        }
    }

    @GetMapping ("/getInventory")
    @ResponseStatus (HttpStatus.OK)
    public List<InventoryResponseDto> inventoryGet () throws Exception {
        try {
           return  inventoryService.inventoryGet();
        } catch (Exception e) {
            throw new DefaultException("No List to return"+e.getMessage());
        }
    }



    @GetMapping("/code")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponseDto> isInStock (@RequestParam ("skuCode") List<String>skuCode) throws Exception {
        try {
            validateSkuCode (skuCode);
            return inventoryService.InventoryServiceData (skuCode);
        } catch (IllegalArgumentException e) {
            log.error ("Invalid SkuCode {}", skuCode);
            throw new DefaultException ("Unable to return data");
        } catch (Exception e) {
            log.error ("Error checking inventory for SKU  code: {}",skuCode,e);
            throw new DefaultException ("Unable to return data");
        }
    }
    public void validateSkuCode( List<String> skuCode) throws Exception {
      boolean resultOutPut =   skuCode.stream ().anyMatch (n->n.isEmpty () || n.isBlank ());
      if (resultOutPut) {
          throw new DefaultException ("unable to return data");
      }

    }




}
