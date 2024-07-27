package com.example.WebDemo.Repository;

import com.example.WebDemo.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReponsitory extends JpaRepository<Product, Integer> {
}
