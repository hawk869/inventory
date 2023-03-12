package com.danielesteban.inventory.services;

import com.danielesteban.inventory.model.Product;
import com.danielesteban.inventory.response.ProductResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProductService {

    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);
}
