/*
 * Copyright 2021-2023 www.jiahui.com
 */
package com.xuyao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.yulichang.annotation.EntityMapping;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.upms.model.EruptUser;

/**
 * @author fei.yang
 * @date 2023/11/8 - 17:33
 */

@Erupt(name = "交期回复跟进")
@Table(name = "delivery_time_track")
@Entity
@Data
public class DeliveryTimeTrack {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "native")
    @Column(name = "id")
    @EruptField
    private Long id;
    @EruptField(views = @View(title = "跟进日期"), edit = @Edit(title = "跟进日期"))
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date trackDate;

    @ManyToOne()
    @TableField("track_user_id")
    @EruptField(views = {@View(title = "跟进人", column = "name")}, edit = @Edit(title = "跟进人", type = EditType.REFERENCE_TREE, search = @Search))
    private EruptUser trackUser;
    @EruptField(views = @View(title = "成本"), edit = @Edit(title = "成本"))
    private Double cost;
    @EruptField(views = @View(title = "备注"), edit = @Edit(title = "备注", type = EditType.TEXTAREA))
    private String remark;
}
