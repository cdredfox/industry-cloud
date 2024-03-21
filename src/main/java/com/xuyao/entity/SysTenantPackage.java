/*
 * Copyright 2021-2023 www.xuyao.info
 */
package com.xuyao.entity;

/**
 * @author yangfei
 * @date 2023/12/30 - 20:44
 */

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Filter;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.BoolType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;
import xyz.erupt.upms.model.base.HyperModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Erupt(name = "租户套餐")
@Table(name = "sys_tenant_package")
@Entity
@Data
public class SysTenantPackage extends HyperModel {
    @EruptField(views = @View(title = "套餐名称"), edit = @Edit(title = "套餐名称",search = @Search(vague = true)  ))
    private String packageName;
    @EruptField(views = @View(title = "套餐说明"), edit = @Edit(title = "套餐说明", type = EditType.TEXTAREA))
    private String remark;
    @EruptField(views = @View(title = "套餐状态"), edit = @Edit(title = "套餐状态", boolType = @BoolType(trueText = "正常", falseText = "停用")))
    private boolean status=true;
}
