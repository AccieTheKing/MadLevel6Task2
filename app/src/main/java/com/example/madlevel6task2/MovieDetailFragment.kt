package com.example.madlevel6task2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.madlevel6task2.ui.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment() {
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAddReminderResult()
        observeMovie()
    }


    private fun observeAddReminderResult() {
        parentFragmentManager.setFragmentResultListener(
            MOVIE_KEY,
            viewLifecycleOwner
        ) { key, bundle ->
            bundle.getString(MOVIE_BUNDLE_KEY)?.let {
                var movieID = it.toInt()
                viewModel.getMovie(movieID)
            }
        }
    }

    private fun observeMovie() {
        viewModel.movie.observe(viewLifecycleOwner, {
            context?.let { it1 ->
                val imageSource ="https://image.tmdb.org/t/p/original/%s";
                Glide.with(it1)
                    .load(String.format(imageSource, it.backdrop_path))
                    .into(backdropImage)
                Glide.with(it1)
                    .load(String.format(imageSource, it.poster_path))
                    .into(imgMovieBanner)
            }
            txtMovieTitle.text = it.title
            movieDate.text = it.release_date
            txtViewRating.text = it.vote_average.toString()
            txtMovieDescription.text = it.overview
        })
    }
}