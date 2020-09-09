package com.chunmiao.spikesystem.service;

import com.chunmiao.spikesystem.dao.StockRepo;
import com.chunmiao.spikesystem.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepo repo;

    public Long getStock(String name) {
        Stock stock = repo.findStockByName(name);
        return stock.getStock();
    }

    public void decrStock(String name){
        Stock stock = repo.findStockByName(name);
        if (stock == null) return;
        stock.setStock(stock.getStock() - 1);
        repo.save(stock);
    }

}
