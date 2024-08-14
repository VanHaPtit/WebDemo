package com.example.WebDemo.Service.Impl;

import com.example.WebDemo.Model.Product;
import com.example.WebDemo.Model.Shipping;
import com.example.WebDemo.Repository.ShippingRepository;
import com.example.WebDemo.Service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingServiceImpl implements ShippingService {
    @Autowired
    private ShippingRepository shippingRepository ;
    @Override
    public Shipping save(Shipping shipping) {
        return shippingRepository.save(shipping);
    }

    @Override
    public void delete(Long id) {
        shippingRepository.deleteById(id);
    }

    @Override
    public Page<Shipping> findAll(Pageable pageInfo) {
        return shippingRepository.findAll(pageInfo);
    }

    @Override
    public List<Shipping> getAll() {
        return shippingRepository.findAll();
    }

    @Override
    public Optional<Shipping> findById(Integer shippingID) {
        return shippingRepository.findById(Long.valueOf(shippingID));
    }


    @Override
    public List<Shipping> findAll() {
        return shippingRepository.findAll();
    }

    @Override
    public Page<Shipping> seachShipping(String keyword, Integer pageNo) {
        List list = seachShipping(keyword);
        Pageable pageable = PageRequest.of(pageNo - 1 , 10);
        Integer start = (int)pageable.getOffset();
        Integer end = (int)((pageable.getOffset()+pageable.getPageSize()) > list.size() ? list.size() : pageable.getOffset() +pageable.getPageSize());
        list = list.subList(start , end) ;
        return new PageImpl<Shipping>(list,pageable , seachShipping(keyword).size());
    }

    @Override
    public List<Shipping> seachShipping(String keyword) {
        return shippingRepository.seachShipping(keyword);
    }


    @Override
    public Page<Shipping> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1 , 10) ;
        return shippingRepository.findAll(pageable);
    }
}
