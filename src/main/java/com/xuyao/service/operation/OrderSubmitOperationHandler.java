/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.service.operation;

import com.xuyao.entity.torder.TaskOrder;
import com.xuyao.entity.torder.TmsOrder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.erupt.annotation.fun.OperationHandler;
import xyz.erupt.jpa.dao.EruptDao;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangfei
 * @date 2024/5/18 - 10:28
 */
@Component
public class OrderSubmitOperationHandler implements OperationHandler<TmsOrder, Void> {

    @Resource
    private EruptDao eruptDao;
    @Override
    @Transactional
    public String exec(List<TmsOrder> data, Void unused, String[] param) {
        //订单提交
        List<TmsOrder> invalidOrders = data.stream().filter(tmsOrder -> !"2".equals(tmsOrder.getOrderStatus())).collect(Collectors.toList());
        if(!invalidOrders.isEmpty()){
            return "msg.error('选择的订单包含了不是已分配状态的订单')";
        }
        data.forEach(tmsOrder -> {
            tmsOrder.setOrderStatus("3");
            eruptDao.merge(tmsOrder);
            TaskOrder taskOrder = eruptDao.lambdaQuery(TaskOrder.class).eq(TaskOrder::getTmsOrder, tmsOrder).one();
            taskOrder.setOrderStatus("3");
            eruptDao.merge(taskOrder);
        });

        return "msg.success('订单提交成功')";
    }
}
