package com.chunmiao.spikesystem.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.chunmiao.spikesystem.config.MyRabbitConfig.STOCK_QUEUE;

@Service
public class MQStockService {

    @Autowired
    private StockService Service;

    /**
     * 订单消费者
     */
    @RabbitListener(queues = STOCK_QUEUE)
    public void decrStock(String name){
        System.out.println("商品减少一个:" + name);
        Service.decrStock(name);
    }
}
