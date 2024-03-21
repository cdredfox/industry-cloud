/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.config;

import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author yangfei
 * @date 2024/1/13 - 15:11
 */
@Configuration
public class RedisOpsConfig {
    @Bean
    @DependsOn("stringRedisTemplate")
    public WxRedisOps wxRedisOps(StringRedisTemplate stringRedisTemplate) {
        return new RedisTemplateWxRedisOps(stringRedisTemplate);
    }
}
