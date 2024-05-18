/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.entity.torder;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.NumberType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;
import xyz.erupt.upms.handler.DictChoiceFetchHandler;
import xyz.erupt.upms.looker.LookerOrg;
import xyz.erupt.upms.model.EruptUser;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 任务分成比例设置
 * @author yangfei
 * @date 2024/5/17 - 06:16
 */
@Erupt(name = "任务分成比例设置")
@Table(name = "tms_task_ratio")
@Entity
@Data
public class OrderTaskRatio extends BaseModel {
    @EruptField(
            views = @View(title = "任务类型"),
            edit = @Edit(
                    title = "任务类型",
                    notNull = true,
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            fetchHandler = DictChoiceFetchHandler.class,
                            fetchHandlerParams = {"tms.taskCode", "5000"}
                    ))
    )
    private String taskCode;
    @ManyToOne()
    @TableField("service_user_id")
    @EruptField(views = {@View(title = "任务人", column = "name")}, edit = @Edit(title = "任务人", type = EditType.REFERENCE_TREE))
    private EruptUser serviceUser;
    @EruptField(views = @View(title = "分成比例(%)"), edit = @Edit(title = "分成比例(%)",numberType = @NumberType))
    private Double taxRate;
}
