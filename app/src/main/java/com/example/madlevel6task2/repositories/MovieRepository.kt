package com.example.madlevel6task2.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.BuildConfig
import com.example.madlevel6task2.api.MovieApi
import com.example.madlevel6task2.models.Movie
import com.example.madlevel6task2.models.MovieResponse
import com.example.madlevel6task2.services.MovieServices

class MovieRepository {
    private val movieService: MovieServices = MovieApi.createApi()
    private val _movies: MutableLiveData<MovieResponse> = MutableLiveData()
    private val _movie: MutableLiveData<Movie> = MutableLiveData()

    val movies: LiveData<MovieResponse> get() = _movies
    val movie: LiveData<Movie> get() = _movie

    suspend fun getMovie(movieID: Int) {
        try {
            val result =
                movieService.getMovie( movie_id = movieID)
            _movie.value = result
        } catch (error: Throwable) {
            throw  MovieApiError("Movie fetching error", error)
        }
    }

    suspend fun getAllMovies(movieYear: Int) {
        try {
            val result =
                movieService.getMovies(api_key = BuildConfig.MOVIE_API_KEY, movieYear = movieYear)
            _movies.value = result
        } catch (error: Throwable) {
            throw  MovieApiError("Movies fetching error", error)
        }
    }

    class MovieApiError(message: String, cause: Throwable) : Throwable(message, cause)
}