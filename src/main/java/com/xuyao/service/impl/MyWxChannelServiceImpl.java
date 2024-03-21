/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.impl.WxChannelServiceImpl;
import me.chanjar.weixin.channel.config.WxChannelConfig;
import org.springframework.stereotype.Service;

/**
 * @author yangfei
 * @date 2024/1/13 - 15:24
 */
@Slf4j
@Service("wxChannelService")
public class MyWxChannelServiceImpl extends WxChannelServiceImpl {

    public MyWxChannelServiceImpl(WxChannelConfig wxChannelConfig) {
        // 初始化配置，不要直接this.config = wxChannelConfig
        this.setConfig(wxChannelConfig);
        log.info("MyWxChannelServiceImpl loading...");

    }
}
