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
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;
import xyz.erupt.upms.model.EruptUser;

import javax.persistence.ManyToOne;

/**
 * @author yangfei
 * @date 2024/5/18 - 10:57
 */
@Erupt(name = "任务分配")
@Data
public class AllocationMode extends BaseModel {
    @ManyToOne()
    @TableField("commission_config_id")
    @EruptField(views = {@View(title = "结算模式", column = "name")}, edit = @Edit(title = "结算模式", type = EditType.REFERENCE_TREE, search = @Search))
    private CommissionConfig commissionConfig;

    @ManyToOne()
    @TableField("service_user_id")
    @EruptField(views = {@View(title = "跟进人", column = "name")}, edit = @Edit(title = "跟进人", type = EditType.REFERENCE_TREE, search = @Search))
    private EruptUser serviceUser;
}
