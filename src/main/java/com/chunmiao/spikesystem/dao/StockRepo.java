package com.chunmiao.spikesystem.dao;

import com.chunmiao.spikesystem.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepo extends JpaRepository<Stock,Long> {

    public Stock findStockByName(String name);
}
