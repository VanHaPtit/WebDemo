package com.example.WebDemo.Service;

import com.example.WebDemo.Model.Category;
import com.example.WebDemo.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void delete(Long id);
    Page<Product> findAll(Pageable pageInfo);
    List<Product> getAll();
    public Boolean create(Product product);
    public Optional<Product> findById(Integer ID);
    public Boolean update(Product product);
    Page<Product> seachProduct(String keyword , Integer pageNo);
    List<Product>seachProduct(String keyword);
    Page<Product> getAll(Integer pageNo);

}
