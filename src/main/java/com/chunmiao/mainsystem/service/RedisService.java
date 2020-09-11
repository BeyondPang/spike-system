package com.chunmiao.mainsystem.service;

import com.chunmiao.mainsystem.dao.StockRe;
import com.chunmiao.mainsystem.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * stock信息缓存到redis
 */
@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private StockRe stockRe;

    public void put(String key, Long value) {
        BoundValueOperations<String, Object> bp = redisTemplate.boundValueOps(key);
        Long count = (Long) bp.get();
        if ( count!= null){
            count = count >= 0 ? count + value : value;
        } else count = value;
        bp.set(count);

        Stock stock = stockRe.findByName(key);
        if (stock == null) {
            stock = new Stock();
            stock.setName(key);
            stock.setStock(0l);
        }
        long l = stock.getStock() + value;
        stock.setStock(l);
        stockRe.save(stock);
    }

    public Long decrBy(String key) {
        BoundValueOperations<String, Object> bp = redisTemplate.boundValueOps(key);
        Long count = (Long) bp.get();
        if (count == null) return -1l;
        if (count >= 0) {
            count--;
            bp.set(count);
        }
        return count;
    }
}
