/*
 * Copyright 2021-2024 www.jiahui.com
 */
package com.xuyao.config;

import com.xuyao.service.wx.MyMessageDuplicateChecker;
import com.xuyao.service.wx.handler.SubscribeHandler;
import com.xuyao.service.wx.handler.UnSubscribeHandler;
import com.xuyao.service.wx.handler.WxMpTextMessageHandler;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.api.WxConsts.EventType;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fei.yang
 * @date 2024/3/22 - 10:21
 */
@Configuration
public class WxJavaConfig {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpTextMessageHandler textHandler;

    @Autowired
    private SubscribeHandler subscribeHandler;

    @Autowired
    private UnSubscribeHandler unSubscribeHandler;

    @Autowired
    private MyMessageDuplicateChecker messageDuplicateChecker;

    @Bean
    public WxMpMessageRouter messageRouter() {
        // 创建消息路由
        final WxMpMessageRouter router = new WxMpMessageRouter(wxMpService);
        router.setMessageDuplicateChecker(messageDuplicateChecker);
        // 添加文本消息路由
        router.rule().async(false).msgType(WxConsts.XmlMsgType.TEXT).handler(textHandler).end();
        router.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT).event(WxConsts.EventType.SUBSCRIBE).handler(subscribeHandler).end();
        router.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT).event(EventType.UNSUBSCRIBE).handler(unSubscribeHandler).end();
        return router;
    }
}
