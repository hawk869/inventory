package com.danielesteban.inventory.dao;

import com.danielesteban.inventory.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryDao extends CrudRepository<Category, Long> {
}
