package com.example.WebDemo.Repository;

import com.example.WebDemo.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("SELECT c FROM Category c WHERE c.categoryName LIKE %?1%")
    List<Category> seachCategory(String keyword);
}
