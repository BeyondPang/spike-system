package com.chunmiao.mainsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> re = new RedisTemplate<>();
        re.setConnectionFactory(redisConnectionFactory);
        re.setKeySerializer(new StringRedisSerializer());
        re.setValueSerializer(new Jackson2JsonRedisSerializer<>(Long.class));   // 不能用generic的Serializer，有存Long取Integer的bug
        re.afterPropertiesSet();
        return re;
    }


}
