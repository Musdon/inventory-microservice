package com.musdon.productservice.service;

import com.musdon.productservice.dto.ProductRequest;
import com.musdon.productservice.model.Product;
import com.musdon.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    public ResponseEntity<ProductRequest> createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(modelMapper.map(savedProduct, ProductRequest.class));
    }

    public ResponseEntity<ProductRequest> updateProduct(ProductRequest productRequest, Long productId){
        boolean isProductExist = productRepository.existsById(productId);
        if (isProductExist){
            Product productToUpdate = productRepository.findById(productId).get();
            productToUpdate.setName(productRequest.getName());
            productToUpdate.setDescription(productRequest.getDescription());
            productToUpdate.setPrice(productRequest.getPrice());
            Product savedProduct = productRepository.save(productToUpdate);
            return ResponseEntity.ok(modelMapper.map(savedProduct, ProductRequest.class));
        }
        return null;
    }

    public ResponseEntity<List<ProductRequest>> fetchAllProducts(int pageNo, int pageSize){
        List<Product> productList = productRepository.findAll().stream()
                .skip(pageNo-1).limit(pageSize)
                .toList();

        return ResponseEntity.ok(productList.stream().map(product -> modelMapper.map(product, ProductRequest.class)).toList());

    }

    public ResponseEntity<ProductRequest> fetchSingleProduct(Long productId){
        boolean isProductExist = productRepository.existsById(productId);
        if (!isProductExist){
            return null;
        }
        Product product = productRepository.findById(productId).get();
        return ResponseEntity.ok(modelMapper.map(product, ProductRequest.class));
    }
}
