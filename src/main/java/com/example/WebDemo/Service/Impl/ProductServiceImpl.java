package com.example.WebDemo.Service.Impl;

import com.example.WebDemo.Model.Product;
import com.example.WebDemo.Repository.ProductReponsitory;
import com.example.WebDemo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductReponsitory productReponsitory ;

    @Override
    public List<Product> findAll() {
        return productReponsitory.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productReponsitory.findById(Math.toIntExact(id));
    }

    @Override
    public Product save(Product product) {
        return productReponsitory.save(product);
    }

    @Override
    public void delete(Long id) {
        productReponsitory.deleteById(Math.toIntExact(id));
    }

    @Override
    public Page<Product> findAll(Pageable pageInfo) {
        return productReponsitory.findAll(pageInfo);
    }

    @Override
    public List<Product> getAll() {
        return productReponsitory.findAll();
    }

    @Override
    public Boolean create(Product product) {
        try {
            this.productReponsitory.save(product);
            return true ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false ;
    }

    @Override
    public Optional<Product> findById(Integer ID) {
        return productReponsitory.findById(ID);
    }

    @Override
    public Boolean update(Product product) {
        try {
            this.productReponsitory.save(product);
            return true ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false ;
    }
}
