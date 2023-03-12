package com.danielesteban.inventory.services;

import com.danielesteban.inventory.dao.ICategoryDao;
import com.danielesteban.inventory.dao.IProductDao;
import com.danielesteban.inventory.model.Category;
import com.danielesteban.inventory.model.Product;
import com.danielesteban.inventory.response.ProductResponseRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private final ICategoryDao categoryDao;
    private final IProductDao productDao;

    public ProductServiceImpl(ICategoryDao categoryDao, IProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @Override
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {
        ProductResponseRest responseRest = new ProductResponseRest();
        List<Product> productList = new ArrayList<>();

        try {
            Optional<Category> category = categoryDao.findById(categoryId);
            if (category.isPresent())
                product.setCategory(category.get());
            else{
                responseRest.setMetadata("respuesta nok","-1", "Categoria no encontrada asociada al producto");
                return new ResponseEntity<>(responseRest, HttpStatus.NOT_FOUND);
            }
            Product productSaved = productDao.save(product);
            productList.add(productSaved);
            responseRest.getProductResponse().setProducts(productList);
            responseRest.setMetadata("respuesta ok", "00", "Producto guardado");
        }
        catch (Exception e){
                e.getStackTrace();
                responseRest.setMetadata("respuesta nok", "-1", "Error al guardar producto");
                return new ResponseEntity<>(responseRest, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(responseRest, HttpStatus.CREATED);
    }
}
