package com.chunmiao.spikesystem.dao;

import com.chunmiao.spikesystem.domain.TOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<TOrder, Long> {

}
