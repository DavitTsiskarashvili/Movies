package com.movies.presentation.home.ui.adapter.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import com.movies.common.extensions.loadImage
import com.movies.databinding.MovieItemBinding
import com.movies.presentation.base.adapter.movie_adapter.BaseMovieAdapter
import com.movies.presentation.base.data.model.MovieUIModel

class FavouriteMovieAdapter : BaseMovieAdapter<MovieUIModel, FavouriteMovieAdapter.MoviesViewHolder>() {

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
                    genreTextView.text = genreString
                    favouritesToggleButton.isChecked = isFavourite

                    root.setOnClickListener {
                        onClickCallback?.invoke(item)
                    }
                    favouritesToggleButton.setOnClickListener {
                        onFavouriteClick?.invoke(item, favouritesToggleButton.isChecked)
                    }
                }
            }
        }
    }

}