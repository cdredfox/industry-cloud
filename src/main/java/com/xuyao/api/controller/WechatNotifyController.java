/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.api.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangfei
 * @date 2024/1/13 - 15:16
 */
@Slf4j
@RestController
@RequestMapping("/wx/notify/{appid}")
public class WechatNotifyController {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WxMpMessageRouter wxMpMessageRouter;

    /**
     * 接收微信服务器的认证消息
     *
     * @param appid     视频号appId(注意这是路径的参数)
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   随机字符串
     * @return echostr
     */
    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authGet(@PathVariable String appid, @RequestParam(name = "signature", required = false) String signature,
        @RequestParam(name = "timestamp", required = false) String timestamp, @RequestParam(name = "nonce", required = false) String nonce,
        @RequestParam(name = "echostr", required = false) String echostr) {
        log.info("\n接收到来自微信服务器的认证消息：signature = [{}], timestamp = [{}], nonce = [{}], echostr = [{}]", signature, timestamp, nonce, echostr);
        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }
        return "非法请求";
    }

    /**
     * 接收微信服务器的消息
     *
     * @param appid        视频号appId(注意这是路径的参数)
     * @param requestBody  消息体
     * @param msgSignature 签名串
     * @param encryptType  加密方式
     * @param signature    微信加密签名
     * @param timestamp    时间戳
     * @param nonce        随机数
     * @return String
     */
    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String post(@PathVariable String appid, @RequestBody String requestBody, @RequestParam(name = "msg_signature", required = false) String msgSignature,
        @RequestParam(name = "encrypt_type", required = false) String encryptType, @RequestParam(name = "signature", required = false) String signature,
        @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce) {
        log.info("\n接收微信请求：[msg_signature=[{}], encrypt_type=[{}], signature=[{}]," + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ", msgSignature,
            encryptType, signature, timestamp, nonce, requestBody);
        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }
        WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
        WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
        if (outMessage == null) {
            return "";
        }
        return outMessage.toXml();
    }
}
