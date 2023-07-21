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
        val movieViewHolder = MoviesViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
//        movieViewHolder.binding.favouritesToggleButton.setOnClickListener {
//            onClickCallback?.invoke(getItem(movieViewHolder.adapterPosition))
//        }
//        movieViewHolder.itemView.setOnClickListener {
//            onClickCallback?.invoke(getItem(movieViewHolder.adapterPosition))
//        }
        return movieViewHolder
    }

    class MoviesViewHolder(val binding: MovieItemBinding) :
        BaseMovieViewHolder<MovieUIModel>(binding) {
        override fun onBindMovie(
            item: MovieUIModel,
            onClickCallback: ((MovieUIModel) -> Unit)?,
            onFavouriteClickCallback: ((MovieUIModel) -> Unit)?,
            ) {
            with(item) {
                with(binding) {
                    posterImageView.loadImage(poster)
                    titleTextView.text = title
                    releaseYearTextView.text = releaseDate
                    ratingTextView.text = rating.toString()

                    root.setOnClickListener {
                        onClickCallback?.invoke(item)
                    }
                    binding.favouritesToggleButton.setOnClickListener {
                        onFavouriteClickCallback?.invoke(item)
                    }
                }
            }
        }
    }

}