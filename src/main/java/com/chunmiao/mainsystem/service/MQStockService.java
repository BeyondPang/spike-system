package com.chunmiao.mainsystem.service;

import com.chunmiao.mainsystem.dao.StockRe;
import com.chunmiao.mainsystem.domain.Stock;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.chunmiao.mainsystem.config.MyRabbitConfig.STOCK_QUEUE;

/**
 * 从MQ拿消息，使库存减少一个
 */
@Service
public class MQStockService {
    @Autowired
    private StockRe stockRe;

    @RabbitListener(queues = STOCK_QUEUE)
    public void decrStock(String orderName) {
        System.out.println("减少数据库的库存");
        Stock stock = stockRe.findByName(orderName);
        if (stock!= null) {
            stock.setStock(stock.getStock() - 1);
            stockRe.save(stock);
        }
    }
}
