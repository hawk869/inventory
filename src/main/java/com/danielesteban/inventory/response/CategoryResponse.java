package com.danielesteban.inventory.response;

import com.danielesteban.inventory.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {

    private List<Category> categories;
}
