package com.movies.presentation.base.adapter.paging_adapter

import com.movies.presentation.base.adapter.movie_adapter.BaseMovieAdapter

abstract class BasePagingMovieAdapter<MODEL : Any, VH : BaseMovieAdapter.BaseMovieViewHolder<MODEL>> :
    BasePagingAdapter<MODEL, VH>() {

    protected var onFavouriteClick: ((MODEL, Boolean) -> Unit)? = null

    fun onFavouriteClickListener(onFavouriteClick: (MODEL, Boolean) -> Unit) {
        this.onFavouriteClick = onFavouriteClick
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let {
            holder.onBindMovie(
                it,
                onClickCallback,
                onFavouriteClick
            )
        }
    }
}
