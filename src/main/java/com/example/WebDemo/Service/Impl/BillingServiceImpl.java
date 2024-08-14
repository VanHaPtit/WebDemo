package com.example.WebDemo.Service.Impl;

import com.example.WebDemo.Model.Billing;
import com.example.WebDemo.Repository.BillingReponsitory;
import com.example.WebDemo.Service.BillingIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BillingServiceImpl implements BillingIService {
    @Autowired
    private BillingReponsitory billingReponsitory ;


    @Override
    public void delete(Long id) {
        billingReponsitory.deleteById(id);
    }

    @Override
    public Page<Billing> findAll(Pageable pageInfo) {
        return billingReponsitory.findAll(pageInfo);
    }

    @Override
    public List<Billing> getAll() {
        return billingReponsitory.findAll();
    }

    @Override
    public Optional<Billing> findById(Integer BillingID) {
        return billingReponsitory.findById(Long.valueOf(BillingID));
    }

    @Override
    public Billing save(Billing billing) {
        return billingReponsitory.save(billing);
    }

    @Override
    public List<Billing> findAll() {
        return billingReponsitory.findAll();
    }
}
