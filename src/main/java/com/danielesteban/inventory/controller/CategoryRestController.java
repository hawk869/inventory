package com.danielesteban.inventory.controller;

import com.danielesteban.inventory.response.CategoryResponseRest;
import com.danielesteban.inventory.services.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
