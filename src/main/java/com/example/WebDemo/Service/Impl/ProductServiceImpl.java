package com.example.WebDemo.Service.Impl;

import com.example.WebDemo.Model.Category;
import com.example.WebDemo.Model.Product;
import com.example.WebDemo.Repository.ProductReponsitory;
import com.example.WebDemo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public Page<Product> seachProduct(String keyword, Integer pageNo) {
        List list = seachProduct(keyword);
        Pageable pageable = PageRequest.of(pageNo - 1 , 10);
        Integer start = (int)pageable.getOffset();
        Integer end = (int)((pageable.getOffset()+pageable.getPageSize()) > list.size() ? list.size() : pageable.getOffset() +pageable.getPageSize());
        list = list.subList(start , end) ;
        return new PageImpl<Product>(list,pageable , seachProduct(keyword).size());
    }

    @Override
    public List<Product> seachProduct(String keyword) {
        return productReponsitory.seachProduct(keyword);
    }

    @Override
    public Page<Product> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1 , 10) ;
        return productReponsitory.findAll(pageable);
    }
}
