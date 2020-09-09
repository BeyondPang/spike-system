package com.chunmiao.spikesystem.controller;

import com.chunmiao.spikesystem.domain.TOrder;
import com.chunmiao.spikesystem.service.RedisService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.chunmiao.spikesystem.config.MyRabbitConfig.*;

@RestController
public class SecController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisService redisService;

    /**
     * 上架10个watch商品
     * @return
     */
    @RequestMapping("/put")
    public String put(){
        redisService.put("watch", 10, 20);
        return "OK";
    }

    /**
     * 参与秒杀
     * @param username
     * @param name
     * @return
     */
    @RequestMapping("/sec")
    public String sec(String username,String name){
        System.out.println("\n" + "----------------------------------------------------------------------------");
        System.out.println("参加秒杀的用户是：" + username +"，秒杀的商品是：" + name);
        Long count = redisService.decrBy("" + name);
        String msg;
        if (count>=0){
            System.out.println("有库存，秒杀用户：" + username +"，秒杀商品：" + name);

            // 发消息给库存队列，库存-1
            rabbitTemplate.convertAndSend(STOCK_EXCHANG,STOCK_ROUTING_KEY,name);

            // 发消息给订单队列，创建新订单
//            Order order = Order.builder().orderName(name).orderUser(username).build();
            TOrder TOrder = new TOrder();
            TOrder.setOrderName(name);
            TOrder.setOrderUser(username);
            rabbitTemplate.convertAndSend(ORDER_EXCHANG,ORDER_ROUTING_KEY, TOrder);
            msg = username +"秒杀" + name + "成功";
            System.out.println(msg);
            return msg;
        } else {
            msg = username +"秒杀" + name + "失败" + "没库存";
            System.out.println(msg);
            return msg;
        }

    }

}
