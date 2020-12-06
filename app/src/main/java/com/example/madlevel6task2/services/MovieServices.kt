package com.example.madlevel6task2.services

import com.example.madlevel6task2.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieServices {
    @GET("/3/discover/movie")
    suspend fun getMovies(
        @Query("api_key") api_key: String,
        @Query("primary_release_year") movieYear: Int
    ): MovieResponse
}