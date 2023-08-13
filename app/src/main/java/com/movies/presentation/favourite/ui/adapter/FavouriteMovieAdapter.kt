package com.movies.presentation.favourite.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.movies.common.extensions.loadImage
import com.movies.databinding.MovieItemBinding
import com.movies.presentation.base.data.model.MovieUIModel
import com.movies.presentation.base.diff_util.DiffUtilCallback

class FavouriteMovieAdapter :
    ListAdapter<MovieUIModel, FavouriteMovieAdapter.MoviesViewHolder>(DiffUtilCallback()) {

    private var onClickCallback: ((MovieUIModel) -> Unit)? = null
    private var onFavouriteClick: ((MovieUIModel, Boolean) -> Unit)? = null

    fun onItemClickListener(onClickCallback: (MovieUIModel) -> Unit) {
        this.onClickCallback = onClickCallback
    }

    fun onFavouriteClickListener(onFavouriteClick: (MovieUIModel, Boolean) -> Unit) {
        this.onFavouriteClick = onFavouriteClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position), onClickCallback, onFavouriteClick)
    }

    class MoviesViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: MovieUIModel,
            onClickCallback: ((MovieUIModel) -> Unit)?,
            onFavouriteClick: ((MovieUIModel, Boolean) -> Unit)?
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