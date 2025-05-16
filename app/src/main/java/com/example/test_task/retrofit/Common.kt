package com.example.test_task.retrofit

object Common {
    private val BASE_URL = "https://gorest.co.in/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}