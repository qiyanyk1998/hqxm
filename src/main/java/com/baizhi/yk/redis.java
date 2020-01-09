package com.baizhi.yk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class redis {
    @Bean
    public Jedis getJedis(){
        return new Jedis("192.168.127.19",6379);
    }

}
