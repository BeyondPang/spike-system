package com.chunmiao.mainsystem.dao;

import com.chunmiao.mainsystem.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRe extends JpaRepository<Stock,Long> {

    Stock findByName(String name);

}
