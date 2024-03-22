/*
 * Copyright 2021-2024 www.jiahui.com
 */
package com.xuyao.service.wx.handler;

import java.util.Map;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

/**
 * @author fei.yang
 * @date 2024/3/22 - 10:29
 */
@Component
public class UnSubscribeHandler implements WxMpMessageHandler {

    @Override
    public WxMpXmlOutMessage handle(final WxMpXmlMessage wxMpXmlMessage, final Map<String, Object> map, final WxMpService wxMpService,
        final WxSessionManager wxSessionManager) throws WxErrorException {

        return null;
    }
}
