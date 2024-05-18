/*
 * Copyright 2021-2024 www.jiahui.com
 */
package com.xuyao.service.wx.handler;

import java.util.Map;

import cn.hutool.core.thread.ThreadUtil;
import com.xuyao.config.WxJavaConfig;
import com.xuyao.service.ChatApiService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author fei.yang
 * @date 2024/3/22 - 10:14
 */
@Component
@Slf4j
public class WxMpTextMessageHandler implements WxMpMessageHandler {


    @Autowired
    private ChatApiService chatApiService;
    @Autowired
    private WxMpService wxMpService;
    @Override
    public WxMpXmlOutMessage handle(final WxMpXmlMessage wxMessage, final Map<String, Object> map, final WxMpService wxMpService,
        final WxSessionManager wxSessionManager) throws WxErrorException {
        String inContent = wxMessage.getContent();
        String outContent = "";
        if(true){
            outContent=chatApiService.chat(inContent);
        }else {
            String appId=wxMpService.getWxMpConfigStorage().getAppId();
            ThreadUtil.execute(() -> {
                wxMpService.switchover(appId);
                String kefuContent = chatApiService.chat(inContent);
                try {
                    String result = wxMpService.getKefuService().sendKefuMessageWithResponse(WxMpKefuMessage.TEXT().content(kefuContent).toUser(wxMessage.getFromUser()).build());
                    log.info("客服消息发送结果:{}", result);
                } catch (WxErrorException e) {
                    log.error("客服消息发送失败", e);
                }
            });
        }
        return WxMpXmlOutMessage.TEXT().content(outContent).fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).build();
    }
}
