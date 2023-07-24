package com.musdon.productservice.controller;

import com.musdon.productservice.dto.ProductRequest;
import com.musdon.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductRequest> createProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @PutMapping("{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductRequest> updateProduct(@RequestBody ProductRequest productRequest,@PathVariable Long productId){
        return productService.updateProduct(productRequest, productId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductRequest>> fetchAllProducts(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "50") int pageSize){
        return productService.fetchAllProducts(pageNo, pageSize);
    }

    @GetMapping("{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductRequest> fetchSingleProduct(@PathVariable Long productId){
        return productService.fetchSingleProduct(productId);
    }
}
