package com.chunmiao.spikesystem.service;

import com.chunmiao.spikesystem.dao.OrderRepo;
import com.chunmiao.spikesystem.domain.TOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepo repo;

    public void createOrder(TOrder TOrder){
        repo.save(TOrder);
    }



}
