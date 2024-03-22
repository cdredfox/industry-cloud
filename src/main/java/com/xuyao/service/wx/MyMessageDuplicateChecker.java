/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.service.wx;

import me.chanjar.weixin.common.api.WxMessageDuplicateChecker;
import org.springframework.stereotype.Component;

/**
 * @author yangfei
 * @date 2024/1/13 - 15:19
 */
@Component
public class MyMessageDuplicateChecker implements WxMessageDuplicateChecker {

    @Override
    public boolean isDuplicate(String messageId) {
        return false;
    }
}
