/*
 * Copyright 2021-2023 www.jiahui.com
 */
package com.xuyao.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.xuyao.repository.DeliveryTimeTrackJpaRepository;
import com.xuyao.api.req.QryDeliveryTimeTrackRequest;
import com.xuyao.entity.DeliveryTimeTrack;
import com.xuyao.service.DeliveryTimeTrackService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fei.yang
 * @date 2023/11/10 - 9:53
 */
@Service
public class DeliveryTimeTrackServiceImpl implements DeliveryTimeTrackService {

    @Autowired
    private DeliveryTimeTrackJpaRepository deliveryTimeTrackJpaRepository;

    @Override
    public List<DeliveryTimeTrack> qryDeliveryTimeTrack(final QryDeliveryTimeTrackRequest qryDeliveryTimeTrackRequest) {
        DateTime queryDate = DateUtil.beginOfDay(DateUtil.parseDateTime(qryDeliveryTimeTrackRequest.getCalendarDate()));
        return deliveryTimeTrackJpaRepository.findAllByTrackDateBetween(DateUtil.beginOfMonth(queryDate),
            DateUtil.offsetDay(DateUtil.endOfMonth(queryDate), 1));
    }
}
