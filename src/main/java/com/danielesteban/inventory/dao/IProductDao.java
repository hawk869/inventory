package com.danielesteban.inventory.dao;

import com.danielesteban.inventory.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductDao extends CrudRepository<Product, Long> {
}
