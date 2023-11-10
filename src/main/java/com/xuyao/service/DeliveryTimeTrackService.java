/*
 * Copyright 2021-2023 www.jiahui.com
 */
package com.xuyao.service;

import com.xuyao.api.req.QryDeliveryTimeTrackRequest;
import com.xuyao.entity.DeliveryTimeTrack;
import java.util.List;

/**
 * @author fei.yang
 * @date 2023/11/10 - 9:51
 */
public interface DeliveryTimeTrackService {

    List<DeliveryTimeTrack> qryDeliveryTimeTrack(QryDeliveryTimeTrackRequest qryDeliveryTimeTrackRequest);
}
