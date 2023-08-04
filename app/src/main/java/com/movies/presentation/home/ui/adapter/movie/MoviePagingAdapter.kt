package com.movies.presentation.home.ui.adapter.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import com.movies.common.extensions.loadImage
import com.movies.databinding.MovieItemBinding
import com.movies.presentation.base.adapter.movie_adapter.BaseMovieAdapter
import com.movies.presentation.base.adapter.paging_adapter.BasePagingMovieAdapter
import com.movies.presentation.base.data.MovieUIModel

class MoviePagingAdapter : BasePagingMovieAdapter<MovieUIModel, MoviePagingAdapter.MoviesViewHolder>() {
    var favouriteMovies: List<MovieUIModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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
        BaseMovieAdapter.BaseMovieViewHolder<MovieUIModel>(binding) {
        override fun onBindMovie(
            item: MovieUIModel,
            onClickCallback: ((MovieUIModel) -> Unit)?,
            onFavouriteClick: ((MovieUIModel, Boolean) -> Unit)?,
        ) {
            val isFavourite = favour

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
                    favouritesToggleButton.setOnClickListener {
                        onFavouriteClick?.invoke(item, favouritesToggleButton.isChecked)
                    }
                }
            }
        }
    }

}