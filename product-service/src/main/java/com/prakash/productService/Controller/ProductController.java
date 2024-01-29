package com.prakash.productService.Controller;


import com.prakash.productService.DTO.ProductRequest;
import com.prakash.productService.DTO.ProductResponse;
import com.prakash.productService.Services.ProductServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductServices productServices;
    public ProductController(ProductServices productServices){
     this.productServices = productServices;
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createProducts (@RequestBody @Valid ProductRequest productRequest) {
        productServices.createProduct(productRequest);
        return "Product has been posted";
    }

    @GetMapping("/get/product")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct(){
        return productServices.getAllProduct();
    }




}
