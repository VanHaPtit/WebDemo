package com.example.WebDemo.Service.Impl;

import com.example.WebDemo.Model.Category;
import com.example.WebDemo.Repository.CategoryRepository;
import com.example.WebDemo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public List<Category> seachCategory(String keyword) {
        return categoryRepository.seachCategory(keyword);
    }

    @Override
    public Page<Category> seachCategory(String keyword, Integer pageNo) {
        List list = seachCategory(keyword);
        Pageable pageable = PageRequest.of(pageNo - 1 , 10);
        Integer start = (int)pageable.getOffset();
        Integer end = (int)((pageable.getOffset()+pageable.getPageSize()) > list.size() ? list.size() : pageable.getOffset() +pageable.getPageSize());
        list = list.subList(start , end) ;
        return new PageImpl<Category>(list,pageable , seachCategory(keyword).size());
    }

    @Override
    public Page<Category> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1 , 10) ;
        return categoryRepository.findAll(pageable);
    }


}
