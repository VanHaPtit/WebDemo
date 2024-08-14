package com.example.WebDemo.Service;

import com.example.WebDemo.Model.Category;
import com.example.WebDemo.Model.Product;
import com.example.WebDemo.Model.Shipping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface ShippingService {
    Shipping save(Shipping shipping) ;
    void delete(Long id);
    Page<Shipping> findAll(Pageable pageInfo);
    List<Shipping> getAll();
    public Optional<Shipping> findById(Integer shippingID);
    public List<Shipping>findAll();
    Page<Shipping> seachShipping(String keyword , Integer pageNo);
    List<Shipping>seachShipping(String keyword);
    Page<Shipping> getAll(Integer pageNo);
}
