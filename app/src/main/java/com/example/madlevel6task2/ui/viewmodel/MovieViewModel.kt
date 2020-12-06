package com.example.madlevel6task2.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.madlevel6task2.repositories.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val movieRepository = MovieRepository()
    private val _errorText: MutableLiveData<String> = MutableLiveData()

    val success = MutableLiveData<Boolean>()
    val movies = movieRepository.movies

    fun getMovies() {
        viewModelScope.launch {
            try {
                movieRepository.getAllMovies()
                success.value = true
            } catch (error: MovieRepository.MovieApiError) {
                _errorText.value = error.message
                success.value = false
                Log.e("Getting products error", error.cause.toString())
            }
        }
    }
}