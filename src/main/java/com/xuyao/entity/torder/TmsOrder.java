/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.entity.torder;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xuyao.service.operation.OrderAllocationOperationHandler;
import com.xuyao.service.operation.OrderSettleOperationHandler;
import com.xuyao.service.operation.OrderSubmitOperationHandler;
import lombok.Data;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_erupt.RowOperation;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.NumberType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;
import xyz.erupt.upms.handler.DictChoiceFetchHandler;
import xyz.erupt.upms.handler.DictCodeChoiceFetchHandler;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author yangfei
 * @date 2024/5/17 - 06:37
 */
@Erupt(name = "业务订单" ,power = @Power(importable = true,export = true), rowOperation = {
        @RowOperation(
                title = "订单分配",
                code = "allocation",
                mode = RowOperation.Mode.MULTI,
                icon = "fa fa-arrow-right",
                eruptClass = AllocationMode.class,
                operationHandler = OrderAllocationOperationHandler.class),
        @RowOperation(
                title = "订单提交",
                code = "submit",
                mode = RowOperation.Mode.MULTI,
                icon = "fa fa-arrow-up",
                operationHandler = OrderSubmitOperationHandler.class),
        @RowOperation(
                title = "生成打款单",
                code = "settle",
                mode = RowOperation.Mode.MULTI,
                icon = "fa fa-money",
                operationHandler = OrderSettleOperationHandler.class)
})
@Table(name = "tms_order")
@Entity
@Data
public class TmsOrder extends BaseModel {

    @ManyToOne()
    @TableField("commission_config_id")
    @EruptField(views = {@View(title = "模式", column = "name")}, edit = @Edit(title = "模式", show = false,type = EditType.REFERENCE_TREE, search = @Search))
    private CommissionConfig commissionConfig;
    @EruptField(views = @View(title = "订单编号"), edit = @Edit(title = "订单编号",notNull = true,search = @Search(vague = true)  ))
    private String orderNo;
    @EruptField(views = @View(title = "订单标题"), edit = @Edit(title = "订单标题",notNull = true,search = @Search(vague = true)  ))
    private String orderTitle;
    @EruptField(views = @View(title = "订单总金额"), edit = @Edit(title = "订单总金额",notNull = true,numberType = @NumberType))
    private Double orderAmount;
    @EruptField(views = @View(title = "买家实付金额"), edit = @Edit(title = "买家实付金额",notNull = true,numberType = @NumberType))
    private Double paidAmount;
    @EruptField(views = @View(title = "商家实收金额"), edit = @Edit(title = "商家实收金额",notNull = true,numberType = @NumberType))
    private Double receivedAmount;
    @EruptField(views = @View(title = "分成金额"), edit = @Edit(show = false,title = "分成金额",numberType = @NumberType))
    private Double commissionAmount;
    @EruptField(views = @View(title = "订单备注"), edit = @Edit(title = "订单备注"))
    private String orderMemo;
    @EruptField(
            views = @View(title = "订单状态"),
            edit = @Edit(
                    title = "订单状态",
                    notNull = true,
                    show = false,
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            fetchHandler = DictCodeChoiceFetchHandler.class,
                            fetchHandlerParams = {"tms.order.orderStatus", "5000"}
                    ),search = @Search(vague = true))
    )
    private String orderStatus="1";
    @EruptField(views = @View(title = "订单付款时间"), edit = @Edit(title = "订单付款时间",search = @Search(vague = true)))
    private Date paymentTime;
    @EruptField(views = @View(title = "确认收货时间"), edit = @Edit(title = "确认收货时间",search = @Search(vague = true)))
    private Date confirmTime;
}
