package com.chunmiao.mainsystem.service;

import com.chunmiao.mainsystem.dao.TOrderRe;
import com.chunmiao.mainsystem.domain.TOrder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.chunmiao.mainsystem.config.MyRabbitConfig.TORDER_QUEUE;
import static com.chunmiao.mainsystem.config.MyRabbitConfig.STOCK_QUEUE;

/**
 * 从MQ中拿消息，创建一个新订单到数据库
 */
@Service
public class MQOrderService {
    @Autowired
    private TOrderRe orderRe;

    @RabbitListener(queues = TORDER_QUEUE)
    public void saveOrder(TOrder order) {
        System.out.println("创建新订单");
        orderRe.save(order);
    }
}
