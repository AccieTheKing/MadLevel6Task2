package com.example.madlevel6task2.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page") var page: Int,
    @SerializedName("results") var results: List<Movie>,
    @SerializedName("total_pages") var total_pages: Int,
    @SerializedName("total_results") var total_results: Int,
)