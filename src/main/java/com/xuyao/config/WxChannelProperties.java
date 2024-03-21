/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangfei
 * @date 2024/1/13 - 14:47
 */
@Data
@Configuration
@ConfigurationProperties(prefix = WxChannelProperties.PREFIX)
public class WxChannelProperties {
    public static final String PREFIX = "wx.channel";

    /**
     * 设置视频号小店的appid
     */
    private String appid;

    /**
     * 设置视频号小店的Secret
     */
    private String secret;

    /**
     * 设置视频号小店消息服务器配置的token
     */
    private String token;

    /**
     * 设置视频号小店消息服务器配置的EncodingAESKey
     */
    private String aesKey;

    /**
     * 消息格式，XML或者JSON
     */
    private String msgDataFormat = "JSON";
}

