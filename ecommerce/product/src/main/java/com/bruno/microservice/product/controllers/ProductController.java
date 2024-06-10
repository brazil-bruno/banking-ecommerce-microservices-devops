package com.bruno.microservice.product.controllers;

import com.bruno.microservice.product.dto.ProductDTO;
import com.bruno.microservice.product.entities.Product;
import com.bruno.microservice.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createNewProduct(@RequestBody ProductDTO productDTO) {
        return productService.createNewProduct(productDTO);
    }

    @GetMapping("/{productID}")
    public Product findProductById(@PathVariable UUID productID) {
        return productService.findProductById(productID);
    }

    @PutMapping("/{productID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Product updateProduct(@RequestBody ProductDTO productDTO, @PathVariable UUID productID) {
        return productService.updateProduct(productDTO, productID);
    }

    @DeleteMapping("/{productID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable UUID productID) {
        productService.deleteProductById(productID);
    }
}
