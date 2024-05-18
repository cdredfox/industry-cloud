/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.service.impl;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.completion.chat.*;
import com.theokanning.openai.embedding.EmbeddingRequest;
import com.theokanning.openai.embedding.EmbeddingResult;
import com.theokanning.openai.service.OpenAiService;
import com.xuyao.service.ChatApiService;
import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yangfei
 * @date 2024/3/22 - 20:18
 */
@Service
@Slf4j
public class ChatApiServiceImpl implements ChatApiService {

    // 请自定义自己的业务id
    private static final String requestIdTemplate = "mycompany-%d";

    @Autowired
    private OpenAiService openAiService;
    @Override
    public String chat(String content) {

        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage systemMessage = new ChatMessage(ChatMessageRole.USER.value(), content);
        messages.add(systemMessage);
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .maxTokens(50000)
                .logitBias(new HashMap<>())
                .build();
        ChatCompletionResult completion = openAiService.createChatCompletion(completionRequest);
        if(CollectionUtils.isEmpty(completion.getChoices())){
            log.info("调用模型失败，返回值{}", JSONUtil.toJsonStr(completion));
            return "机器人忙碌，等会再试";
        }
        return completion.getChoices().get(0).getMessage().getContent();
    }

    @Override
    public Flux<String> streamChat(String content) {
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage systemMessage = new ChatMessage(ChatMessageRole.USER.value(), content);
        messages.add(systemMessage);
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .maxTokens(50000)
                .logitBias(new HashMap<>())
                .build();
        Flowable<ChatCompletionChunk> completion = openAiService.streamChatCompletion(completionRequest);
        return Flux.from(completion.filter(item->item.getChoices().get(0).getMessage().getContent()!=null).map(item -> item.getChoices().get(0).getMessage().getContent()));
    }

    @Override
    public String embedding(String content){
        List<String> data= Lists.newArrayList(content);
        EmbeddingRequest embeddingRequest = EmbeddingRequest.builder()
                .input(data)
                .model("embedding-2")
                .build();
        EmbeddingResult embedding = openAiService.createEmbeddings(embeddingRequest);
        if(CollectionUtils.isEmpty(embedding.getData())){
            log.info("调用模型失败，返回值{}", JSONUtil.toJsonStr(embedding));
            return "机器人忙碌，等会再试";
        }
        return embedding.getData().get(0).getEmbedding().toString();
    }
}
