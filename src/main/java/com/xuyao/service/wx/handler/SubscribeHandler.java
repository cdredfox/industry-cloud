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
 * @date 2024/3/22 - 10:27
 */
@Component
public class SubscribeHandler implements WxMpMessageHandler {

    @Override
    public WxMpXmlOutMessage handle(final WxMpXmlMessage wxMessage, final Map<String, Object> map, final WxMpService wxMpService,
        final WxSessionManager wxSessionManager) throws WxErrorException {
        return WxMpXmlOutMessage.TEXT().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).content("欢迎关注").build();
    }
}
