/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.entity.torder;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;
import xyz.erupt.upms.handler.DictCodeChoiceFetchHandler;
import xyz.erupt.upms.looker.LookerSelf;
import xyz.erupt.upms.model.EruptUser;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author yangfei
 * @date 2024/5/18 - 09:22
 */
@Erupt(name = "任务明细单" ,power = @Power(add = false,edit = false,importable = false,export = true))
@Table(name = "tms_task_order")
@Entity
@Data
public class TaskOrder extends BaseModel {

    @ManyToOne()
    @TableField("tms_order_id")
    @EruptField(views = {@View(title = "订单编号", column = "orderNo")}, edit = @Edit(title = "订单编号", type = EditType.REFERENCE_TREE, search = @Search))
    private TmsOrder tmsOrder;

    @ManyToOne()
    @TableField("commission_config_id")
    @EruptField(views = {@View(title = "结算模式", column = "name")}, edit = @Edit(title = "结算模式", type = EditType.REFERENCE_TREE, search = @Search))
    private CommissionConfig commissionConfig;

    @ManyToOne()
    @TableField("service_user_id")
    @EruptField(views = {@View(title = "任务人", column = "name")}, edit = @Edit(title = "任务人", type = EditType.REFERENCE_TREE, search = @Search))
    private EruptUser serviceUser;

    @EruptField(
            views = @View(title = "任务状态"),
            edit = @Edit(
                    title = "任务状态",
                    notNull = true,
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            fetchHandler = DictCodeChoiceFetchHandler.class,
                            fetchHandlerParams = {"tms.taskOrder.orderStatus", "5000"}
                    ),search = @Search(vague = true))
    )
    private String orderStatus;
    @EruptField(views = @View(title = "任务金额"), edit = @Edit(title = "任务金额"))
    private Double taskAmount;

}
