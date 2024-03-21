/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.service.impl;

import com.xuyao.config.WxChannelProperties;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.config.WxChannelConfig;
import me.chanjar.weixin.channel.config.impl.WxChannelRedisConfigImpl;
import me.chanjar.weixin.common.redis.WxRedisOps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author yangfei
 * @date 2024/1/13 - 15:08
 */
@Service("wxChannelConfig")
@Slf4j
public class MyWxChannelConfigImpl extends WxChannelRedisConfigImpl implements WxChannelConfig {
    private final WxChannelProperties properties;
    public MyWxChannelConfigImpl(WxRedisOps wxRedisOps, WxChannelProperties properties) {
        super(wxRedisOps, "wx:channel:");
        log.info("MyWxChannelConfigImpl loading...");
        this.properties = properties;
        this.config(properties);
    }

    protected void config(WxChannelProperties properties) {
        this.setAppid(StringUtils.trimToNull(properties.getAppid()));
        this.setSecret(StringUtils.trimToNull(properties.getSecret()));
        this.setToken(StringUtils.trimToNull(properties.getToken()));
        this.setAesKey(StringUtils.trimToNull(properties.getAesKey()));
        this.setMsgDataFormat(StringUtils.trimToNull(properties.getMsgDataFormat()));
        this.setRetrySleepMillis(1000);
        this.setMaxRetryTimes(5);
    }
}
