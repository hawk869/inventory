package com.danielesteban.inventory.services;

import com.danielesteban.inventory.model.Product;
import com.danielesteban.inventory.response.ProductResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProductService {

    ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);
    ResponseEntity<ProductResponseRest> searchById(Long id);
    ResponseEntity<ProductResponseRest> searchByName(String name);
    ResponseEntity<ProductResponseRest> deleteProduct(Long id);
    ResponseEntity<ProductResponseRest> getAllProducts();
    ResponseEntity<ProductResponseRest> updateProduct(Product product, Long categoryId, Long productId);
}
