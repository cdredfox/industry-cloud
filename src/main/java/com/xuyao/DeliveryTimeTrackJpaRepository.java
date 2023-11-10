/*
 * Copyright 2021-2023 www.jiahui.com
 */
package com.xuyao;

import com.xuyao.entity.DeliveryTimeTrack;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fei.yang
 * @date 2023/11/10 - 13:49
 */
@Repository
public interface DeliveryTimeTrackJpaRepository extends JpaRepository<DeliveryTimeTrack, Long> {

    List<DeliveryTimeTrack> findAllByTrackDateBetween(Date trackDateStart, Date trackDateEnd);
}
