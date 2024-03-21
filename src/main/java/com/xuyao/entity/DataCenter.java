/*
 * Copyright 2021-2023 www.xuyao.info
 */
package com.xuyao.entity;

import lombok.Data;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Drill;
import xyz.erupt.annotation.sub_erupt.Link;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;
import xyz.erupt.upms.looker.LookerSelf;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * @author yangfei
 * @date 2023/12/2 - 10:22
 * EruptDictItem@IMPORTABLE
 *
 */
@Erupt(name = "数据中心",power = @Power(importable = true,export = true),drills = {
        @Drill(code = "drill",
                title = "数据明细",
                //最终生成的表达式为：EruptTest.id = DrillErupt.eruptTest.id
                link = @Link(column = "id",                // 当前表关联列
                        linkErupt = OrderData.class, // 目标关联表
                        joinColumn = "dataCenterId"))  // 目标表关联列
})
@Table(name = "kd_data_center")
@Entity
@Data
public class DataCenter  extends LookerSelf {
    @EruptField(views = @View(title = "名称"), edit = @Edit(title = "名称",search = @Search(vague = true)  ))
    private String name;
    @EruptField(views = @View(title = "编码"), edit = @Edit(title = "编码"))
    private String code;
}
