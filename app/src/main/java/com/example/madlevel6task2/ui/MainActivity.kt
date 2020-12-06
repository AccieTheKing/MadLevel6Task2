package com.example.madlevel6task2.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel6task2.R
import com.example.madlevel6task2.models.Movie
import com.example.madlevel6task2.ui.recyclers.adapters.MovieAdapter
import com.example.madlevel6task2.ui.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val movies = arrayListOf<Movie>()
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        val gridLayoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        movieAdapter = MovieAdapter(movies, ::onSeeMovieDetails)
        rvMovies.layoutManager = gridLayoutManager
        rvMovies.adapter = movieAdapter
        btnSubmit.setOnClickListener {
            onSearchMovies()
        }
    }

    private fun onSearchMovies() {
        val movieYear = txtMovieYear.text.toString().toInt()
        viewModel.getMovies(movieYear)

        viewModel.movies.observe(this, {
            movies.clear()
            val movieList: List<Movie> = it.results
            movies.addAll(movieList)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun onSeeMovieDetails(movie: Movie) {
        Log.d("Movie test", movie.toString())
    }
}