/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.service.impl;

import me.chanjar.weixin.common.api.WxMessageDuplicateChecker;

/**
 * @author yangfei
 * @date 2024/1/13 - 15:19
 */
public class MyMessageDuplicateChecker implements WxMessageDuplicateChecker {
    @Override
    public boolean isDuplicate(String s) {
        return false;
    }
}
