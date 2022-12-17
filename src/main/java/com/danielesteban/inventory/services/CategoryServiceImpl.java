package com.danielesteban.inventory.services;

import com.danielesteban.inventory.dao.ICategoryDao;
import com.danielesteban.inventory.model.Category;
import com.danielesteban.inventory.response.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {
        CategoryResponseRest responseRest = new CategoryResponseRest();
        try {
            List<Category> categories = (List<Category>) categoryDao.findAll();
            responseRest.getCategoryResponse().setCategories(categories);
            responseRest.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        }
        catch (Exception e){
            responseRest.setMetadata("Respuesta nok", "-1", "Error al consultar las categorias");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.OK);
    }
}
