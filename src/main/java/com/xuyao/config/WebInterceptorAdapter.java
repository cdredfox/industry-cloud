/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.config;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author yangfei
 * @date 2024/1/13 - 14:48
 */
public class WebInterceptorAdapter implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> aConverters) {
        aConverters.removeIf (aConverter -> (aConverter instanceof MappingJackson2XmlHttpMessageConverter));
    }
}
