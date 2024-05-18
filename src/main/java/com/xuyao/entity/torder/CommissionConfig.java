/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.entity.torder;

import lombok.Data;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;
import xyz.erupt.upms.looker.LookerSelf;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yangfei
 * @date 2024/5/18 - 09:36
 */
@Erupt(name = "佣金模式")
@Table(name = "tms_commission_config")
@Entity
@Data
public class CommissionConfig extends BaseModel {

    @EruptField(views = @View(title = "模式名称"), edit = @Edit(title = "模式名称",notNull = true ))
    private String name;
    @EruptField(views = @View(title = "佣金比例(%)"), edit = @Edit(title = "佣金比例(%)" ,notNull = true))
    private double rate;
}
