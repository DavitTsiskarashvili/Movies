package com.movies.presentation.base.adapter

import androidx.viewbinding.ViewBinding

abstract class BaseMovieAdapter<MODEL : Any, VH : BaseMovieAdapter.BaseMovieViewHolder<MODEL>> :
    BaseAdapter<MODEL, VH>() {

    var onFavouriteClickCallback: ((MODEL) -> Unit)? = null

    fun onFavouriteClickListener(onFavouriteClickCallback: (MODEL) -> Unit) {
        this.onFavouriteClickCallback = onFavouriteClickCallback
    }

    abstract class BaseMovieViewHolder<MODEL : Any>(binding: ViewBinding) :
        BaseViewHolder<MODEL>(binding) {
        abstract fun onBindMovie(
            item: MODEL,
            onClickCallback: ((MODEL) -> Unit)?,
            onFavouriteClickCallback: ((MODEL) -> Unit)?
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
