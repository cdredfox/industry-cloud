/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.config;

import com.zhipu.oapi.ClientV4;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangfei
 * @date 2024/3/22 - 20:11
 */
@Data
@Configuration
@ConfigurationProperties(prefix = OApiConfig.PREFIX)
public class OApiConfig {
    public static final String PREFIX = "oapi";

    private String apiSecretKey;

    @Bean
    public ClientV4 clientV4(){
        return new ClientV4.Builder(apiSecretKey).build();
    }
}
