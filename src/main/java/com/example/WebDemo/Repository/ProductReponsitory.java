package com.example.WebDemo.Repository;

import com.example.WebDemo.Model.Category;
import com.example.WebDemo.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductReponsitory extends JpaRepository<Product, Integer> {
    @Query("SELECT c FROM Product c WHERE c.productName LIKE %?1%")
    List<Product> seachProduct(String keyword);
}
