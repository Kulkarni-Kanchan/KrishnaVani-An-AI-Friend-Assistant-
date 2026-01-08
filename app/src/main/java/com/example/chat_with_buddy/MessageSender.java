package com.example.chat_with_buddy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MessageSender {
    @POST("webhook")
    Call<List<BotResponse>> sendMessage(@Body UserMessage userMessage);
}


