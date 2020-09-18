package com.chunmiao.mainsystem.controller;


import com.chunmiao.mainsystem.domain.TOrder;
import com.chunmiao.mainsystem.service.RedisService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.chunmiao.mainsystem.config.MyRabbitConfig.*;

@RestController
public class Test {
    @Autowired
    private RedisService redisService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/put")
    String put(@RequestParam String orderName, @RequestParam Long count) {
        redisService.put(orderName, count);
        return "上架商品\n" + orderName + ":" + count;
    }

    @RequestMapping("/sec")
    String sec(String userName, String orderName) {
        String msg = "秒杀用户：" + userName + "\n" + "秒杀商品： " + orderName;
        System.out.println("\n---------------------------------------------");
        System.out.println("秒杀用户：" + userName + "\n" + "秒杀商品： " + orderName);
        Long count = redisService.decrBy(orderName);
        count = count >= 0 ? count : -1;
        // 秒杀成功
        System.out.println("当前商品数量为： " + (count + 1));
        if (count >= 0) {
            System.out.println("库存充足");
            // 创建新订单
            rabbitTemplate.convertAndSend(TORDER_EXCHANG,TORDER_ROUTING_KEY,
                    TOrder.builder()
                            .orderName(orderName)
                            .orderUser(userName)
                            .build());
            // 创建库存-1消息
            rabbitTemplate.convertAndSend(STOCK_EXCHANG,STOCK_ROUTING_KEY,orderName);
            System.out.println("秒杀成功");
            msg +=  "成功";
        } else {
            System.out.println("库存不足");
            msg += "失败";
        }
        return msg;
    }
}
