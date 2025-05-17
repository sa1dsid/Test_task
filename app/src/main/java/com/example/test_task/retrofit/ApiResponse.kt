package com.example.test_task.retrofit

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("results") val results: List<Result>
)
