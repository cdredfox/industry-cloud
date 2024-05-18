/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.service;

import reactor.core.publisher.Flux;

/**
 * @author yangfei
 * @date 2024/3/22 - 20:17
 */
public interface ChatApiService {

    public String chat(String content);

    public Flux<String> streamChat(String content);

    public String embedding(String content);
}