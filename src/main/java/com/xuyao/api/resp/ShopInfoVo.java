/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.api.resp;

import lombok.Data;

/**
 * @author yangfei
 * @date 2024/1/13 - 15:15
 */
@Data
public class ShopInfoVo {
    /** 店铺名称 */
    private String nickname;

    /** 店铺头像URL */
    private String headImgUrl;

    /** 店铺类型，目前为"企业"或"个体工商户" */
    private String subjectType;
}
