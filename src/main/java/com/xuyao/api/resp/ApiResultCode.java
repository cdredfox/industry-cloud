/*
 * Copyright 2021-2023 www.jiahui.com
 */
package com.xuyao.api.resp;

/**
 * @author fei.yang
 * @date 2023/11/9 - 14:04
 */
public enum ApiResultCode implements IErrorCode {
    /**
     * 操作成功
     */
    SUCCESS(0, "操作成功"),
    /**
     * 操作失败
     */
    FAILED(500, "操作失败"),
    ;
    private long code;
    private String message;

    private ApiResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
