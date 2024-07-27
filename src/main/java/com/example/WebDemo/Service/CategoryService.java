package com.example.WebDemo.Service;

import com.example.WebDemo.Model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void delete(Long id);
    Page<Category> findAll(Pageable pageInfo);
    List<Category> getAll();
    public Boolean create(Category category);
    public Optional<Category> findById(Integer categoryID);
    public Boolean update(Category category);
    public Category save(Category category) ;
    public List<Category>findAll();
}
