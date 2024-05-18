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
import xyz.erupt.annotation.sub_field.Readonly;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;
import xyz.erupt.upms.handler.DictCodeChoiceFetchHandler;
import xyz.erupt.upms.model.EruptUser;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author yangfei
 * @date 2024/5/18 - 11:23
 */
@Erupt(name = "付款账单" ,power = @Power(add = false,export = true))
@Table(name = "tms_payment_order")
@Entity
@Data
public class PaymentOrder extends BaseModel {


    @ManyToOne()
    @TableField("service_user_id")
    @EruptField(views = {@View(title = "收款人", column = "name")}, edit = @Edit(title = "收款人", readonly = @Readonly,type = EditType.REFERENCE_TREE, search = @Search))
    private EruptUser serviceUser;
    @EruptField(views = @View(title = "付款金额"), edit = @Edit(title = "付款金额", readonly = @Readonly))
    private Double paymentAmount;

    @EruptField(
            views = @View(title = "付款状态"),
            edit = @Edit(
                    title = "付款状态",
                    notNull = true,
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            fetchHandler = DictCodeChoiceFetchHandler.class,
                            fetchHandlerParams = {"tms.payment.orderStatus", "5000"}
                    ),search = @Search(vague = true))
    )
    private String paymentStatus;
    @EruptField(views = @View(title = "付款时间"), edit = @Edit(title = "付款时间"))
    private Date paymentTime;
}
