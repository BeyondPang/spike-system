package com.chunmiao.spikesystem.service;

import com.chunmiao.spikesystem.domain.TOrder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.chunmiao.spikesystem.config.MyRabbitConfig.ORDER_QUEUE;

@Service
public class MQOrderService {

    @Autowired
    private OrderService orderService;

    /**
     * 订单消费者
     * @param TOrder
     */
    @RabbitListener(queues = ORDER_QUEUE)
    public void createOrder(TOrder TOrder){
        System.out.println("收到订单" + '\n' + "用户：" + TOrder.getOrderUser() + '\n' + "商品：" + TOrder.getOrderName());
        orderService.createOrder(TOrder);
    }
}
