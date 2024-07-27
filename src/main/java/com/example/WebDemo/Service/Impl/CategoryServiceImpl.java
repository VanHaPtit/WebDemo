package com.example.WebDemo.Service.Impl;

import com.example.WebDemo.Model.Category;
import com.example.WebDemo.Repository.CategoryRepository;
import com.example.WebDemo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Boolean create(Category category) {
        try {
            this.categoryRepository.save(category);
            return true ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false ;
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public Page<Category> findAll(Pageable pageInfo) {
        return categoryRepository.findAll(pageInfo);
    }

    @Override
    public Optional<Category> findById(Integer categoryID) {
        return categoryRepository.findById(categoryID);
    }

    @Override
    public Boolean update(Category category) {
        try {
            this.categoryRepository.save(category);
            return true ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false ;
    }


    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
