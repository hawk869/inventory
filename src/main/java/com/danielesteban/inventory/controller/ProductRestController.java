package com.danielesteban.inventory.controller;

import com.danielesteban.inventory.model.Product;
import com.danielesteban.inventory.response.ProductResponseRest;
import com.danielesteban.inventory.services.IProductService;
import com.danielesteban.inventory.util.ProductExcelExporter;
import com.danielesteban.inventory.util.Util;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {

    private final IProductService productService;

    public ProductRestController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<?> save(
            @RequestParam MultipartFile photo,
            @RequestParam String name,
            @RequestParam int price,
            @RequestParam int quantity,
            @RequestParam Long categoryId) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setPhoto(Util.compressZLib(photo.getBytes()));

        return productService.save(product, categoryId);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return productService.searchById(id);
    }
    @GetMapping("/products/filter/{name}")
    public ResponseEntity<?> searchProductByName(@PathVariable String name){
        return productService.searchByName(name);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        return productService.getAllProducts();
    }
    @PutMapping("/products/{productId}")
    public ResponseEntity<?> updateProduct(
            @RequestParam MultipartFile photo,
            @RequestParam String name,
            @RequestParam int price,
            @RequestParam int quantity,
            @RequestParam Long categoryId,
            @PathVariable Long productId) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setPhoto(Util.compressZLib(photo.getBytes()));

        return productService.updateProduct(product, categoryId, productId);
    }
    @GetMapping("/products/export/excel")
    public  void exportToExcel(HttpServletResponse response) throws IOException{
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=result_product";
        response.setHeader(headerKey, headerValue);

        ResponseEntity<ProductResponseRest> productResponse = productService.getAllProducts();
        ProductExcelExporter excelExporter;
        excelExporter = new ProductExcelExporter(Objects.requireNonNull(productResponse.getBody()).getProductResponse().getProducts());
        excelExporter.export(response);
    }
}
