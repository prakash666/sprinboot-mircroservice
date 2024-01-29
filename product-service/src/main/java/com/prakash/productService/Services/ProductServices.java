package com.prakash.productService.Services;


import com.prakash.productService.DTO.ProductRequest;
import com.prakash.productService.DTO.ProductResponse;
import com.prakash.productService.Entity.Product;
import com.prakash.productService.Repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServices {


    private final ProductRepository productRepository;

    public ProductServices(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product{} is saved",product.getId());
    }

    public List<ProductResponse> getAllProduct() {
       List<Product> products = productRepository.findAll();
      return products.stream().map(product -> mapToProductResponse(product)).collect(Collectors.toList());
    }
    private ProductResponse mapToProductResponse (Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
