package com.danielesteban.inventory.services;

import com.danielesteban.inventory.dao.ICategoryDao;
import com.danielesteban.inventory.model.Category;
import com.danielesteban.inventory.response.CategoryResponseRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @Transactional
public class CategoryServiceImpl implements ICategoryService{

    private final ICategoryDao categoryDao;

    public CategoryServiceImpl(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

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
            return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {
        CategoryResponseRest responseRest = new CategoryResponseRest();
        List<Category> categoryList = new ArrayList<>();

        try {
            Optional<Category> category = categoryDao.findById(id);
            if (category.isPresent()){
                categoryList.add(category.get());
                responseRest.getCategoryResponse().setCategories(categoryList);
                responseRest.setMetadata("Respuesta ok", "00", "Categoria encontrada");
            }
            else {
                responseRest.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
                return new ResponseEntity<>(responseRest, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            responseRest.setMetadata("Respuesta nok", "-1", "Error al consultar por id");
            e.getStackTrace();
            return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryResponseRest> save(Category category) {
        CategoryResponseRest responseRest = new CategoryResponseRest();
        List<Category> categoryList = new ArrayList<>();

        try {
            Category categorySaved = categoryDao.save(category);
            if(categorySaved != null){
                categoryList.add(categorySaved);
                responseRest.getCategoryResponse().setCategories(categoryList);
                responseRest.setMetadata("Respuesta ok", "00", "Categoria guardada");
            }else {
                responseRest.setMetadata("Respuesta nok", "-1", "Categoria no guardada");
                return new ResponseEntity<>(responseRest, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            responseRest.setMetadata("Respuesta nok", "-1", "Error al guardar la categoria");
            e.getStackTrace();
            return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
        CategoryResponseRest responseRest = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();

        try {
            Optional<Category> searchCategory = categoryDao.findById(id);
            if (searchCategory.isPresent()){
                searchCategory.get().setName(category.getName());
                searchCategory.get().setDescription(category.getDescription());
                list.add(categoryDao.save(searchCategory.get()));
                responseRest.getCategoryResponse().setCategories(list);
                responseRest.setMetadata("Respuesta ok", "00", "Categoria actualizada");
                return new ResponseEntity<>(responseRest, HttpStatus.CREATED);
            }
            else {
                responseRest.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
                return new ResponseEntity<>(responseRest, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            responseRest.setMetadata("Respuesta nok", "-1", "Error al actualizar la categoria");
            e.getStackTrace();
            return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
