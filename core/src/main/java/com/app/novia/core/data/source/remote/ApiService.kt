package com.app.novia.core.data.source.remote

import com.app.novia.core.domain.model.ChatEntity
import com.app.novia.core.domain.model.NewsResponse
import com.app.novia.core.domain.model.UserResponseEntity
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("report")
    suspend fun sendChat(@Body message: JsonObject?): ChatEntity

    @POST("user")
    suspend fun addUser(@Body user: JsonObject?): UserResponseEntity

    @GET("news")
    suspend fun getNews(): NewsResponse
}