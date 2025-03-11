package com.enigma.majumundur.controller;

import com.enigma.majumundur.constant.ApiBash;
import com.enigma.majumundur.entity.Product;
import com.enigma.majumundur.model.request.ProductRequest;
import com.enigma.majumundur.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiBash.PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest){
        Product createProduct=productService.create(productRequest);
        return ResponseEntity.ok(createProduct);
    }
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction
    ) {
        Page<Product> products = productService.getAll(page, size, sortBy, direction);
        return ResponseEntity.ok(products);
    }

    @GetMapping(ApiBash.GET_BY_ID)
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.findByIdOrThrowNotFound(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping(ApiBash.GET_BY_ID)
    public ResponseEntity<Product> updateProduct(
            @PathVariable String id,
            @RequestBody ProductRequest productRequest
    ) {
        Product updatedProduct = productService.updateById(id, productRequest);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping(ApiBash.GET_BY_ID)
    public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
        Product deletedProduct = productService.deleteById(id);
        return ResponseEntity.ok(deletedProduct);
    }
}
