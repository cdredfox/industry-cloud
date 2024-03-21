/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.service.impl;

import com.xuyao.api.resp.ShopInfoVo;
import com.xuyao.service.ChannelApiService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.shop.ShopInfoResponse;
import me.chanjar.weixin.channel.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yangfei
 * @date 2024/1/13 - 15:22
 */
@Slf4j
@Service
public class ChannelApiServiceImpl implements ChannelApiService {
    @Autowired
    private WxChannelService wxChannelService;
    @Override
    public ShopInfoVo getShopInfo() {
        ShopInfoVo shopInfoVo = new ShopInfoVo();
        try {
            // 获取店铺信息
            ShopInfoResponse response = wxChannelService.getBasicService().getShopInfo();
            if (!response.isSuccess()) {
                log.error("获取店铺信息失败, {}", JsonUtils.encode(response));
                return shopInfoVo;
            }
            // 将结果封装到vo中
            if (response.getInfo() != null) {
                shopInfoVo.setNickname(response.getInfo().getNickname());
                shopInfoVo.setHeadImgUrl(response.getInfo().getHeadImgUrl());
                shopInfoVo.setSubjectType(response.getInfo().getSubjectType());
            }
        } catch (Exception e) {
            log.error("获取店铺信息失败", e);
        }
        return shopInfoVo;
    }

    public void getSharerProductQrcode(){

    }
}
