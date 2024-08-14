package com.example.WebDemo.Service.Impl;

import com.example.WebDemo.Model.Billing;
import com.example.WebDemo.Model.CustomerOrder;
import com.example.WebDemo.Repository.CustomerOrderRepository;
import com.example.WebDemo.Service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
    @Autowired
    private CustomerOrderRepository customerOrderRepository ;
    @Override
    public Optional<CustomerOrder> findById(Long ID) {
        return customerOrderRepository.findById(Long.valueOf(ID));
    }
}
