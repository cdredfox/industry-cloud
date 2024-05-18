/*
 * Copyright 2021-2024 www.xuyao.info
 */
package com.xuyao.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.client.OpenAiApi;
import com.theokanning.openai.service.OpenAiService;
import lombok.Data;
import okhttp3.OkHttpClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.time.Duration;
import java.util.concurrent.ExecutorService;

/**
 * @author yangfei
 * @date 2024/4/4 - 10:19
 */
@Data
@Configuration
@ConfigurationProperties(prefix = OpenAIConfig.PREFIX)
public class OpenAIConfig {
    public static final String PREFIX = "openai";

    private String apiSecretKey;

    private String baseUrl;

    private int timeout;

    @Bean
    public OpenAiService openAiService(){

        ObjectMapper mapper = OpenAiService.defaultObjectMapper();
        OkHttpClient client = OpenAiService.defaultClient(apiSecretKey,  Duration.ofSeconds(timeout));
        Retrofit retrofit = defaultRetrofit(client, mapper);

        OpenAiApi api = retrofit.create(OpenAiApi.class);
        ExecutorService executorService = client.dispatcher().executorService();

       return new OpenAiService(api,executorService);
    }

    public Retrofit defaultRetrofit(OkHttpClient client, ObjectMapper mapper) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
