package com.app.novia.core.data.source.remote

import com.app.novia.core.domain.model.ChatEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("get-chatbot-response")
    suspend fun sendChat(@Query("msg") message: String?): ChatEntity
}