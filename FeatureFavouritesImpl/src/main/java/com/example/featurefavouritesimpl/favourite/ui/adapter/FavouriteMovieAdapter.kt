package com.example.featurefavouritesimpl.favourite.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.commonpresentation.databinding.MovieItemBinding
import com.commonpresentation.presentation.base.data.model.MovieUIModel
import com.commonpresentation.presentation.base.view_holder.MoviesViewHolder
import com.commonpresentation.utils.DiffUtilCallback

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
                onFavouriteClick.invoke(getItem(adapterPosition))
            }
            binding.root.setOnClickListener {
                onClickCallback.invoke(getItem(adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}