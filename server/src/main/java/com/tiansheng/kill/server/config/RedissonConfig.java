package com.tiansheng.kill.server.config;/**
 * Created by Administrator on 2019/7/2.
 */

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @作者 itsheng
 * @GitHub https://github.com/hellojava-ts-hdu
 * @博客 http://itsheng.top:8082/
 * @时间  2020/3/2  22:09
 * redisson通用化配置
 */

@Configuration
public class RedissonConfig {

    @Autowired
    private Environment env;

    @Bean
    public RedissonClient redissonClient(){
        Config config=new Config();
        config.useSingleServer()
                .setAddress(env.getProperty("redis.config.host"))
                .setPassword(env.getProperty("spring.redis.password"));
        RedissonClient client=Redisson.create(config);
        return client;
    }


}