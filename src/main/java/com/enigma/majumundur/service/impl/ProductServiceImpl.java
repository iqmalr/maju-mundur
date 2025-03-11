package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.entity.Product;
import com.enigma.majumundur.entity.UserAccount;
import com.enigma.majumundur.model.request.ProductRequest;
import com.enigma.majumundur.repository.ProductRepository;
import com.enigma.majumundur.repository.UserAccountRepository;
import com.enigma.majumundur.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserAccountRepository userAccountRepository;

    @Override
    public Product create(ProductRequest productRequest) {
        UserAccount merchant = userAccountRepository.findById(productRequest.getMerchantId()).orElseThrow(() -> new RuntimeException("Merchant not found"));
        Product product = Product.builder()
                .merchant(merchant)
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .build();
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Page<Product> getAll(int page, int size, String sortBy, String direction) {
        if (page <= 0) page = 1;
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findByIdOrThrowNotFound(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product updateById(String id, ProductRequest productRequest) {
        Product existingProduct = findByIdOrThrowNotFound(id);
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setStock(productRequest.getStock());

        return productRepository.saveAndFlush(existingProduct);
    }

    @Override
    public Product deleteById(String id) {
        Product product = findByIdOrThrowNotFound(id);
        productRepository.delete(product);
        return product;
    }

}
