package com.example.test_task.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitServices {
    @GET("character")
    suspend fun getCharacterList(): Response<ApiResponse>
}