/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.api.controller;

import com.xuyao.api.resp.ApiResult;
import com.xuyao.api.resp.ShopInfoVo;
import com.xuyao.service.ChannelApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangfei
 * @date 2024/1/13 - 15:13
 */
@RestController
@RequestMapping("/channel")
public class WxChannelController {
    @Autowired
    private ChannelApiService channelApiService;

    /**
     * 获取店铺信息
     *
     * @return Result
     */
    @GetMapping("/shopInfo")
    public ApiResult<ShopInfoVo> getShopInfo() {
        return ApiResult.success(channelApiService.getShopInfo());
    }
}
