/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.service.operation;

import com.xuyao.entity.torder.PaymentOrder;
import com.xuyao.entity.torder.TaskOrder;
import com.xuyao.entity.torder.TmsOrder;
import org.springframework.stereotype.Component;
import xyz.erupt.annotation.fun.OperationHandler;
import xyz.erupt.jpa.dao.EruptDao;
import xyz.erupt.upms.model.EruptUser;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yangfei
 * @date 2024/5/18 - 11:18
 */
@Component
public class OrderSettleOperationHandler implements OperationHandler<TmsOrder, Void> {

    @Resource
    private EruptDao eruptDao;
    @Override
    @Transactional
    public String exec(List<TmsOrder> data, Void unused, String[] param) {
        List<TmsOrder> invalidOrders = data.stream().filter(tmsOrder -> !"3".equals(tmsOrder.getOrderStatus())).collect(Collectors.toList());
        if(!invalidOrders.isEmpty()){
            return "msg.error('选择的订单包含了不是已提交状态的订单')";
        }

        Map<EruptUser,PaymentOrder> paymentList=new HashMap<>();
        data.forEach(tmsOrder -> {
            tmsOrder.setOrderStatus("4");
            eruptDao.merge(tmsOrder);
            TaskOrder taskOrder = eruptDao.lambdaQuery(TaskOrder.class).eq(TaskOrder::getTmsOrder, tmsOrder).one();
            taskOrder.setOrderStatus("4");

            PaymentOrder paymentOrder=paymentList.get(taskOrder.getServiceUser());
            if(paymentOrder==null){
                paymentOrder=new PaymentOrder();
                paymentOrder.setPaymentAmount(0d);
                paymentOrder.setPaymentStatus("1");
                paymentOrder.setServiceUser(taskOrder.getServiceUser());
                paymentOrder.setTaskOrderList(new ArrayList<>());
                paymentList.put(taskOrder.getServiceUser(),paymentOrder);
            }
            paymentOrder.setPaymentAmount(paymentOrder.getPaymentAmount()+ taskOrder.getTaskAmount());
            paymentOrder.getTaskOrderList().add(taskOrder);
        });

        paymentList.forEach((key,value)->{
            eruptDao.persist(value);
            value.getTaskOrderList().forEach(taskOrder -> {
                taskOrder.setPaymentOrder(value);
                eruptDao.merge(taskOrder);
            });
        });

        return "msg.success('订单结算成功')";
    }
}
