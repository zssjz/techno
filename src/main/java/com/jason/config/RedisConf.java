package com.jason.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by Jason on 2018/5/2.
 */
//@Configuration
public class RedisConf {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate (RedisConnectionFactory factory, ObjectMapper mapper) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(factory);

//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        RedisSerializer<String> redisSerializer = new StringRedisSerializer();

        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(mapper));

        // key的序列化规则
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // value的序列化规则
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(mapper));
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(mapper));

        return redisTemplate;
    }
}
