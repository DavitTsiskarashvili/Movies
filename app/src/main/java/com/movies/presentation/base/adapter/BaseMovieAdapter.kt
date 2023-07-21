package com.movies.presentation.base.adapter

import androidx.viewbinding.ViewBinding

abstract class BaseMovieAdapter<MODEL : Any, VH : BaseMovieAdapter.BaseMovieViewHolder<MODEL>> :
    BaseAdapter<MODEL, VH>() {

    private var onFavouriteClickCallback: ((MODEL, Boolean) -> Unit)? = null

    fun onFavouriteClickListener(onFavouriteClickCallback: (MODEL, Boolean) -> Unit) {
        this.onFavouriteClickCallback = onFavouriteClickCallback
    }

    abstract class BaseMovieViewHolder<MODEL : Any>(binding: ViewBinding) :
        BaseViewHolder<MODEL>(binding) {
        abstract fun onBindMovie(
            item: MODEL,
            onClickCallback: ((MODEL) -> Unit)?,
            onFavouriteClickCallback: ((MODEL, Boolean) -> Unit)?
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBindMovie(
            getItem(position),
            onClickCallback,
            onFavouriteClickCallback
        )
    }
}
