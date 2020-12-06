package com.example.madlevel6task2.services

import com.example.madlevel6task2.BuildConfig
import com.example.madlevel6task2.models.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieServices {
    @GET("/movie")
    suspend fun getMovies(@Query("api_key") api_key: String = BuildConfig.MOVIE_API_KEY): List<Movie>
}