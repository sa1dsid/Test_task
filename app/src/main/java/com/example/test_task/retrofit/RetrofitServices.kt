package com.example.test_task.retrofit

import com.example.test_task.Item
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("public/v2/posts")
    fun getMovieList(): Call<MutableList<Item>>
}