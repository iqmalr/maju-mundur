package com.enigma.majumundur.service;

import com.enigma.majumundur.entity.Product;
import com.enigma.majumundur.model.request.ProductRequest;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product create(ProductRequest productRequest);

    Page<Product> getAll(int page, int size, String sortBy, String direction);

    Product findByIdOrThrowNotFound(String id);

    Product updateById(String id, ProductRequest productRequest);

    Product deleteById(String id);
}
