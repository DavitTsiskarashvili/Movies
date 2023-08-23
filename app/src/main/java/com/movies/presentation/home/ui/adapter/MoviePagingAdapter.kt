package com.movies.presentation.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.movies.databinding.MovieItemBinding
import com.movies.presentation.base.data.model.MovieUIModel
import com.movies.presentation.base.view_holder.MoviesViewHolder
import com.movies.presentation.utils.DiffUtilCallback

class MoviePagingAdapter(
    private val onClickCallback: (MovieUIModel) -> Unit,
    private val onFavouriteClick: (MovieUIModel, Boolean) -> Unit
) : PagingDataAdapter<MovieUIModel, MoviesViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MoviesViewHolder(binding).apply {

            binding.favouritesToggleButton.setOnClickListener {
                val currentItem = getItem(bindingAdapterPosition)!!

                val newIsFavourite = !currentItem.isFavourite
                onFavouriteClick.invoke(currentItem, newIsFavourite)
                currentItem.isFavourite = newIsFavourite
                notifyItemChanged(bindingAdapterPosition)
            }
            binding.root.setOnClickListener {
                val currentItem = getItem(bindingAdapterPosition)!!
                onClickCallback.invoke(currentItem)
            }
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

}