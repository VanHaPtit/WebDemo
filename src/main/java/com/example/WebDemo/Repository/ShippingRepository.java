package com.example.WebDemo.Repository;

import com.example.WebDemo.Model.Product;
import com.example.WebDemo.Model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShippingRepository extends JpaRepository<Shipping , Long> {
    @Query("SELECT c FROM Shipping c WHERE c.company LIKE %?1%")
    List<Shipping> seachShipping(String keyword);
}
