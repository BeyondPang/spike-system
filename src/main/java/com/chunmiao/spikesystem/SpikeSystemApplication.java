package com.chunmiao.spikesystem;

import com.chunmiao.spikesystem.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpikeSystemApplication {
    @Autowired
    private RedisService redisService;

    public static void main(String[] args) {
        SpringApplication.run(SpikeSystemApplication.class, args);
    }




}
