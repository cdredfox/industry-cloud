/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.api.controller;

import com.xuyao.service.ChatApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yangfei
 * @date 2024/4/4 - 10:38
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatApiService chatApiService;


    @GetMapping()
    public String chat(String msg){
        return chatApiService.chat(msg);
    }

    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamChat(String msg, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        return chatApiService.streamChat(msg);
    }
    @GetMapping("/embedding")
    public String embedding(String content){
        return chatApiService.embedding(content);
    }
}
