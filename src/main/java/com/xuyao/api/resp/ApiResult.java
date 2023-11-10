/*
 * Copyright 2021-2023 www.jiahui.com
 */
package com.xuyao.api.resp;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author fei.yang
 * @date 2023/11/9 - 14:03
 */
@Data
public class ApiResult<T> {

    private long code;
    private boolean success;
    private String msg;
    private T data;

    protected ApiResult() {

    }

    protected ApiResult(long code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.success = code == ApiResultCode.SUCCESS.getCode();
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<T>(ApiResultCode.SUCCESS.getCode(), ApiResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> ApiResult<T> success() {
        return new ApiResult<T>(ApiResultCode.SUCCESS.getCode(), ApiResultCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> ApiResult<T> success(T data, String message) {
        return new ApiResult<T>(ApiResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> ApiResult<T> failed(IErrorCode errorCode) {
        return new ApiResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param message   错误信息
     */
    public static <T> ApiResult<T> failed(IErrorCode errorCode, String message) {
        if (null == errorCode) {
            errorCode = ApiResultCode.FAILED;
        }
        if (StringUtils.isBlank(message)) {
            message = errorCode.getMessage();
        }
        return new ApiResult<T>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> ApiResult<T> failed(String message) {
        return new ApiResult<T>(ApiResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> ApiResult<T> failed() {
        return failed(ApiResultCode.FAILED);
    }
}
