package com.example.WebDemo.Service;

import com.example.WebDemo.Model.Billing;
import com.example.WebDemo.Model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BillingIService {
    void delete(Long id);
    Page<Billing> findAll(Pageable pageInfo);
    List<Billing> getAll();
    public Optional<Billing> findById(Integer BillingID);
    public Billing save(Billing billing) ;
    public List<Billing>findAll();
}
