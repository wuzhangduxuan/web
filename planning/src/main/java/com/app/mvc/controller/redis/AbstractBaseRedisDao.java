package com.app.mvc.controller.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Created by Administrator on 2017/1/25.
 */
public abstract class AbstractBaseRedisDao <K,V>{

    @Autowired
    public RedisTemplate<K,V> redisTemplate;

    public void setRedisTemplate(RedisTemplate<K,V> redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    public RedisSerializer<String> getRedisSerializer(){
        return redisTemplate.getStringSerializer();
    }

}
