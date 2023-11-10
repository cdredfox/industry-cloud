/*
 * Copyright 2021-2023 www.jiahui.com
 */
package com.xuyao.api.req;

import lombok.Data;

/**
 * @author fei.yang
 * @date 2023/11/9 - 14:01
 */
@Data
public class QryDeliveryTimeTrackRequest {

    /**
     * 查询的日期
     */
    private String calendarDate;
}
