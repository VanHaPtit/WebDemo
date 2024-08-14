package com.example.WebDemo.Service;

import com.example.WebDemo.Model.Billing;
import com.example.WebDemo.Model.CustomerOrder;

import java.util.Optional;

public interface CustomerOrderService {
    Optional<CustomerOrder> findById(Long ID);
}
