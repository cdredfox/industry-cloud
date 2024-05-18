/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.service.operation;

import com.xuyao.entity.torder.AllocationMode;
import com.xuyao.entity.torder.CommissionConfig;
import com.xuyao.entity.torder.TmsOrder;
import com.xuyao.entity.torder.TaskOrder;
import javafx.concurrent.Task;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.erupt.annotation.fun.OperationHandler;
import xyz.erupt.jpa.dao.EruptDao;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangfei
 * @date 2024/5/18 - 09:19
 */
@Component
public class OrderAllocationOperationHandler implements OperationHandler<TmsOrder, AllocationMode> {
    @Resource
    private EruptDao eruptDao;

    @Override
    @Transactional
    public String exec(List<TmsOrder> data, AllocationMode allocationInfo, String[] param) {
            List<TmsOrder> invalidOrders = data.stream().filter(tmsOrder -> !"1".equals(tmsOrder.getOrderStatus())).collect(Collectors.toList());
            if(!invalidOrders.isEmpty()){
                return "msg.error('选择的订单包含了不是待分配状态的订单')";
            }
            //订单分配
            data.forEach(tmsOrder -> {
                tmsOrder.setOrderStatus("2");
                CommissionConfig commissionConfig = eruptDao.findById(CommissionConfig.class, allocationInfo.getCommissionConfig().getId());
                BigDecimal b = BigDecimal.valueOf(tmsOrder.getReceivedAmount() * (commissionConfig.getRate() / 100));
                tmsOrder.setCommissionAmount(b.setScale(2, RoundingMode.HALF_UP).doubleValue());
                tmsOrder.setCommissionConfig(allocationInfo.getCommissionConfig());
                eruptDao.merge(tmsOrder);
                //创建任务单
                TaskOrder taskOrder=new TaskOrder();
                taskOrder.setTmsOrder(tmsOrder);
                taskOrder.setOrderStatus("2");
                taskOrder.setTaskAmount(tmsOrder.getCommissionAmount());
                taskOrder.setServiceUser(allocationInfo.getServiceUser());
                taskOrder.setCommissionConfig(allocationInfo.getCommissionConfig());
                eruptDao.persist(taskOrder);
            });
            return "msg.success('订单分配成功')";
    }
}
