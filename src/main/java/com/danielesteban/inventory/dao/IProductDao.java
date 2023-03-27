package com.danielesteban.inventory.dao;

import com.danielesteban.inventory.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductDao extends CrudRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);
}
