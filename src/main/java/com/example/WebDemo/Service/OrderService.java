package com.example.WebDemo.Service;


import com.example.WebDemo.Model.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    List<CustomerOrder> findAll();

    Optional<CustomerOrder> findById(Long id);
    CustomerOrder save(CustomerOrder order);
    void delete(Long id);
    Page<CustomerOrder> search(String keyword, CustomerOrder order);

    void saveAll(List<CustomerOrder> orders);
}

