package com.danielesteban.inventory.controller;

import com.danielesteban.inventory.model.Category;
import com.danielesteban.inventory.response.CategoryResponseRest;
import com.danielesteban.inventory.services.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

    private final ICategoryService service;

    public CategoryRestController(ICategoryService service) {
        this.service = service;
    }

    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchCategories(){
        return service.search();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> searchCategoryById(@PathVariable Long id){
        return service.searchById(id);
    }

    @PostMapping("/categories")
    public ResponseEntity<?> save(@RequestBody Category category){
        return service.save(category);
    }
    @PutMapping("/categories/{id}")
    public ResponseEntity<?> update(@RequestBody Category category, @PathVariable Long id){
        return service.update(category, id);
    }
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return service.deleteById(id);
    }
}
