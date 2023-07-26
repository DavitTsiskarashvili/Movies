package com.movies.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.movies.common.extensions.loadImage
import com.movies.databinding.MovieItemBinding
import com.movies.presentation.base.adapter.BaseAdapter
import com.movies.presentation.model.movie.MovieUIModel

class MovieAdapter : BaseAdapter<MovieUIModel>() {

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

    class MoviesViewHolder(private val binding: MovieItemBinding) :
        BaseViewHolder<MovieUIModel>(binding) {
        override fun onBind(item: MovieUIModel, onClickCallback: ((MovieUIModel) -> Unit)?) {
            with(item) {
                with(binding) {
                    posterImageView.loadImage(poster)
                    titleTextView.text = title
                    releaseYearTextView.text = releaseDate
                    ratingTextView.text = rating.toString()

                    root.setOnClickListener {
                        onClickCallback?.invoke(item)
                    }
                }
            }
        }
    }

}