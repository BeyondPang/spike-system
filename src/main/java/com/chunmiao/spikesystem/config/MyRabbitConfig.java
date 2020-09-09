package com.chunmiao.spikesystem.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRabbitConfig {

    public final static String ORDER_EXCHANG = "ORDER_EXCHANG";

    public final static String ORDER_QUEUE = "ORDER_QUEUE";

    public final static String ORDER_ROUTING_KEY = "ORDER_ROUTING_KEY";

    public final static String STOCK_EXCHANG = "STOCK_EXCHANG";

    public final static String STOCK_QUEUE = "STOCK_QUEUE";

    public final static String STOCK_ROUTING_KEY = "STOCK_ROUTING_KEY";

    /**
     *  创建交换机
     *  创建队列
     *  通过路由键绑定交换机和队列
     */


    @Bean
    public Exchange getOrderExchange(){
        return ExchangeBuilder.directExchange(ORDER_EXCHANG).build();
    }
    @Bean
    public Queue getOrderQueue(){
        return QueueBuilder.nonDurable(ORDER_QUEUE).build();
    }
    @Bean
    public Binding bindOrder(){
        return BindingBuilder.bind(getStockQueue()).to(getStockExchange()).with(ORDER_ROUTING_KEY).noargs();
    }


    @Bean
    public Exchange getStockExchange(){
        return ExchangeBuilder.directExchange(STOCK_EXCHANG).build();
    }
    @Bean
    public Queue getStockQueue(){
        return QueueBuilder.nonDurable(STOCK_QUEUE).build();
    }
    @Bean
    public Binding bindStock(){
        return BindingBuilder.bind(getStockQueue()).to(getStockExchange()).with(STOCK_ROUTING_KEY).noargs();
    }
}
