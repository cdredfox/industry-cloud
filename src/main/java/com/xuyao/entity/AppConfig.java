/*
 * Copyright 2021-2023 www.xuyao.info
 */
package com.xuyao.entity;

import lombok.Data;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.sub_erupt.Drill;
import xyz.erupt.annotation.sub_erupt.Link;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.upms.looker.LookerSelf;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yangfei
 * @date 2023/12/2 - 11:08
 */
@Erupt(name = "官网配置")
@Table(name = "kd_app_config")
@Entity
@Data
public class AppConfig extends LookerSelf {

    private String name;
    private String notes;
    private String logo;
}
