package com.danielesteban.inventory.response;

import com.danielesteban.inventory.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {

    List<Product> products;
}
