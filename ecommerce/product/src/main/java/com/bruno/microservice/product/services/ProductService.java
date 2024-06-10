package com.bruno.microservice.product.services;

import com.bruno.microservice.product.dto.ProductDTO;
import com.bruno.microservice.product.entities.Product;
import com.bruno.microservice.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product createNewProduct(ProductDTO productDTO) {
        Product entity = Product.builder()
                .productName(productDTO.getProductName())
                .productDescription(productDTO.getProductDescription())
                .productPrice(productDTO.getProductPrice())
                .productQuantity(productDTO.getProductQuantity())
                .build();
        return productRepository.save(entity);
    }

    public Product findProductById(UUID productID) {
        return productRepository.findById(productID).get();
    }

    public Product updateProduct(ProductDTO productDTO, UUID productID) {
        Product entity = productRepository.findById(productID).get();
        entity.setProductName(productDTO.getProductName());
        entity.setProductDescription(productDTO.getProductDescription());
        entity.setProductPrice(productDTO.getProductPrice());
        entity.setProductQuantity(productDTO.getProductQuantity());
        return productRepository.save(entity);
    }

    public void deleteProductById(UUID productID) {
        productRepository.deleteById(productID);
    }
}
