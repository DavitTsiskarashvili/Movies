package com.movies.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.movies.common.extensions.loadImage
import com.movies.databinding.MovieItemBinding
import com.movies.presentation.base.adapter.BaseMovieAdapter
import com.movies.presentation.model.movie.MovieUIModel

class MovieAdapter : BaseMovieAdapter<MovieUIModel, MovieAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        return MoviesViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class MoviesViewHolder(val binding: MovieItemBinding) :
        BaseMovieViewHolder<MovieUIModel>(binding) {
        override fun onBindMovie(
            item: MovieUIModel,
            onClickCallback: ((MovieUIModel) -> Unit)?,
            onFavouriteClick: ((MovieUIModel, Boolean) -> Unit)?,
        ) {
            with(item) {
                with(binding) {
                    posterImageView.loadImage(poster)
                    titleTextView.text = title
                    releaseYearTextView.text = releaseDate
                    ratingTextView.text = rating.toString()
                    favouritesToggleButton.isChecked = isFavourite

                    root.setOnClickListener {
                        onClickCallback?.invoke(item)
                    }
                    binding.favouritesToggleButton.setOnClickListener {
                        onFavouriteClick?.invoke(item, binding.favouritesToggleButton.isChecked)
                    }
                }
            }
        }
    }

}