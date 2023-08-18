package com.movies.presentation.base.adapter.paging_adapter

import androidx.viewbinding.ViewBinding
import com.movies.presentation.base.adapter.movie_adapter.BaseMovieAdapter

abstract class BasePagingMovieAdapter<MODEL : Any, VH : BaseMovieAdapter.BaseMovieViewHolder<MODEL>> :
    BasePagingAdapter<MODEL, VH>() {

    private var onFavouriteClick: ((MODEL, Boolean) -> Unit)? = null

    fun onFavouriteClickListener(onFavouriteClick: (MODEL, Boolean) -> Unit) {
        this.onFavouriteClick = onFavouriteClick
    }

    abstract class BaseMovieViewHolder<MODEL : Any>(binding: ViewBinding) :
        BaseViewHolder<MODEL>(binding) {
        abstract fun onBindMovie(
            item: MODEL,
            onClickCallback: ((MODEL) -> Unit)?,
            onFavouriteClick: ((MODEL, Boolean) -> Unit)?
        )
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
