/*
 * Copyright 2021-2023 www.jiahui.com
 */
package com.xuyao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import xyz.erupt.core.annotation.EruptScan;

/**
 * @author fei.yang
 * @date 2023/11/8 - 17:27
 */
@SpringBootApplication
@EntityScan
@EruptScan
@MapperScan("com.xuyao.mapper")
@EnableJpaRepositories
public class IndustryCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndustryCloudApplication.class, args);
    }
}
