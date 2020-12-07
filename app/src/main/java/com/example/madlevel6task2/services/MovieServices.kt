package com.example.madlevel6task2.services

import com.example.madlevel6task2.BuildConfig
import com.example.madlevel6task2.models.Movie
import com.example.madlevel6task2.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServices {
    @GET("/3/discover/movie")
    suspend fun getMovies(
        @Query("api_key") api_key: String,
        @Query("primary_release_year") movieYear: Int
    ): MovieResponse

    @GET("/3/movie/{movie_id}?api_key=${BuildConfig.MOVIE_API_KEY}")
    suspend fun getMovie(
        @Path("movie_id") movie_id: Int
    ): Movie
}