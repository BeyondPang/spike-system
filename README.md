# SpringBoot+JPA+MySql+Redis秒杀系统
技术栈：SpringBoot, MySql, Redis, RabbitMQ, JPA,(lombok)
## Controller
/put  : 上架 "watch"商品10个
    @RequestMapping("/put")
    	String put(@RequestParam String orderName, @RequestParam Long count)
/sec  : 秒杀购买商品  
    @RequestMapping("/sec")  
    	String sec(String userName, String orderName)

## Guide

