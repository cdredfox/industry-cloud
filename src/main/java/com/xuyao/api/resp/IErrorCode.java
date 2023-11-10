/*
 * Copyright 2021-2023 www.jiahui.com
 */
package com.xuyao.api.resp;

/**
 * @author fei.yang
 * @date 2023/11/9 - 14:06
 */
public interface IErrorCode {
    /**
     * 获取错误码
     * @return
     */
    long getCode();

    /**
     * 获取错误描述
     * @return
     */
    String getMessage();
}
