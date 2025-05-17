package com.example.test_task.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("character")
    fun getCharacterList(): Call<ApiResponse>
}