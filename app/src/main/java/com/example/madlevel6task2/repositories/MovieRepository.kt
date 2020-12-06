package com.example.madlevel6task2.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.api.MovieApi
import com.example.madlevel6task2.models.Movie
import com.example.madlevel6task2.services.MovieServices

class MovieRepository {
    private val movieService: MovieServices = MovieApi.createApi()
    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()

    val movies: LiveData<List<Movie>> get() = _movies

    suspend fun getAllMovies() {
        try {
            val result = movieService.getMovies()
            _movies.value = result
        } catch (error: Throwable) {
            throw  MovieApiError("Movie fetching error", error)
        }
    }

    class MovieApiError(message: String, cause: Throwable) : Throwable(message, cause)
}