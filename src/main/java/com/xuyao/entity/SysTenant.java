/*
 * Copyright 2021-2023 www.xuyao.info
 */
package com.xuyao.entity;

/**
 * @author yangfei
 * @date 2023/12/30 - 21:02
 */

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.BoolType;
import xyz.erupt.annotation.sub_field.sub_edit.ReferenceTreeType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.upms.model.base.HyperModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Erupt(name = "租户信息")
@Table(name = "sys_tenant")
@Entity
@Data
public class SysTenant extends HyperModel {
    @EruptField(views = @View(title = "租户编码"), edit = @Edit(title = "租户编码",search = @Search(vague = true)  ))
    private String tenantId;
    @EruptField(views = @View(title = "企业名称"), edit = @Edit(title = "企业名称"))
    private String companyName;
    @ManyToOne()
    @TableField("package_id")
    @EruptField(views = {@View(title = "租户套餐", column = "packageName")}, edit = @Edit(title = "租户套餐", type = EditType.REFERENCE_TREE,referenceTreeType = @ReferenceTreeType(
            label = "packageName"),search = @Search))
    private SysTenantPackage packageId;
    @EruptField(views = @View(title = "过期时间"), edit = @Edit(title = "过期时间"))
    private Date expireTime;
    @EruptField(views = @View(title = "用户数量"), edit = @Edit(title = "用户数量"))
    private int accountCount;
    @EruptField(views = @View(title = "租户状态"), edit = @Edit(title = "租户状态",boolType = @BoolType(trueText = "正常", falseText = "停用")))
    private boolean status=true;
    @EruptField(views = @View(title = "联系人"), edit = @Edit(title = "联系人"))
    private String contactUserName;
    @EruptField(views = @View(title = "联系电话"), edit = @Edit(title = "联系电话"))
    private String contactPhone;
    @EruptField(views = @View(title = "统一社会信用代码"), edit = @Edit(title = "统一社会信用代码"))
    private String licenseNumber;
    @EruptField(views = @View(title = "地址"), edit = @Edit(title = "地址"))
    private String address;
    @EruptField(views = @View(title = "简介"), edit = @Edit(title = "简介", type = EditType.TEXTAREA))
    private String intro;
    @EruptField(views = @View(title = "域名"), edit = @Edit(title = "域名"))
    private String domain;
    @EruptField(views = @View(title = "备注"), edit = @Edit(title = "备注", type = EditType.TEXTAREA))
    private String remark;

}
