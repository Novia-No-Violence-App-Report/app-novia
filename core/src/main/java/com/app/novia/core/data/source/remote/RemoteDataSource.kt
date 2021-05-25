package com.app.novia.core.data.source.remote

import com.app.novia.core.domain.model.ChatEntity
import com.salomohutapea.movieapp.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun sendChat(message: String?): Flow<ApiResponse<ChatEntity>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.sendChat(message)
                lateinit var data: ChatEntity

                if (response.message?.isNotEmpty() == true) {
                    response.senderIsBot = true
                    data = response
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }
}