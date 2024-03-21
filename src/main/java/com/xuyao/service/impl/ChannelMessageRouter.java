/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.service.impl;

import me.chanjar.weixin.channel.message.WxChannelMessageRouter;
import org.springframework.stereotype.Service;

/**
 * @author yangfei
 * @date 2024/1/13 - 15:19
 */
@Service
public class ChannelMessageRouter extends WxChannelMessageRouter {
    public ChannelMessageRouter() {
        super();
        this.setMessageDuplicateChecker(new MyMessageDuplicateChecker());
    }
}
