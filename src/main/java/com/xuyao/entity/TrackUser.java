/*
 * Copyright 2021-2023 www.xuyao.info
 */
package com.xuyao.entity;

import lombok.Data;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yangfei
 * @date 2023/11/12 - 07:17
 */
@Erupt(name = "跟进人")
@Table(name = "track_user")
@Entity
@Data
public class TrackUser extends BaseModel {
    @EruptField(views = @View(title = "姓名"), edit = @Edit(title = "姓名"))
    private String name;
}
