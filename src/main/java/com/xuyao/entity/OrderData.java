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
import xyz.erupt.upms.looker.LookerSelf;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yangfei
 * @date 2023/12/2 - 10:29
 */
@Erupt(name = "数据明细",power = @Power(importable = true,export = true))
@Table(name = "kd_order_data")
@Entity
@Data
public class OrderData extends LookerSelf {
    private Long dataCenterId;
    @EruptField(views = @View(title = "收件电话"), edit = @Edit(title = "收件电话",search = @Search(vague = true)  ))
    private String phone;
    @EruptField(views = @View(title = "收件人"), edit = @Edit(title = "收件人",search = @Search(vague = true)  ))
    private String userName;
    @EruptField(views = @View(title = "快递单号"), edit = @Edit(title = "快递单号",search = @Search(vague = true)  ))
    private String trackNo;

}
