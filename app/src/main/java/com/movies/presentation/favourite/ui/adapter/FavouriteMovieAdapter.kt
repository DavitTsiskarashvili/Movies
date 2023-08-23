package com.movies.presentation.favourite.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.movies.databinding.MovieItemBinding
import com.movies.presentation.base.data.model.MovieUIModel
import com.movies.presentation.base.view_holder.MoviesViewHolder
import com.movies.presentation.utils.DiffUtilCallback

class FavouriteMovieAdapter(
    private val onClickCallback: ((MovieUIModel) -> Unit),
    private val onFavouriteClick: ((MovieUIModel) -> Unit)
) : ListAdapter<MovieUIModel, MoviesViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MoviesViewHolder(binding).apply {
            binding.favouritesToggleButton.setOnClickListener {
                onFavouriteClick.invoke(getItem(bindingAdapterPosition))
            }
            binding.root.setOnClickListener {
                onClickCallback.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}