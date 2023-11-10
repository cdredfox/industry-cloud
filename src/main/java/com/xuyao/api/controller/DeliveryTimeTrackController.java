/*
 * Copyright 2021-2023 www.jiahui.com
 */
package com.xuyao.api.controller;

import com.xuyao.api.req.QryDeliveryTimeTrackRequest;
import com.xuyao.api.resp.ApiResult;
import com.xuyao.service.DeliveryTimeTrackService;
import com.xuyao.entity.DeliveryTimeTrack;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fei.yang
 * @date 2023/11/9 - 13:55
 */
@RestController
@RequestMapping("/deliveryTrack")
public class DeliveryTimeTrackController {

    @Autowired
    private DeliveryTimeTrackService deliveryTimeTrackService;

    @PostMapping("list")
    public ApiResult<List<DeliveryTimeTrack>> qryDeliveryTimeTrack(@RequestBody QryDeliveryTimeTrackRequest qryDeliveryTimeTrackRequest) {
        return ApiResult.success(deliveryTimeTrackService.qryDeliveryTimeTrack(qryDeliveryTimeTrackRequest));
    }
}
