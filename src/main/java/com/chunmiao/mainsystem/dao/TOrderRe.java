package com.chunmiao.mainsystem.dao;

import com.chunmiao.mainsystem.domain.TOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TOrderRe extends JpaRepository<TOrder,Long> {
}
