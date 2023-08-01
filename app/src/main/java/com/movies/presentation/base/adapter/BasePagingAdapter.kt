package com.movies.presentation.base.adapter

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BasePagingAdapter<MODEL : Any, VH:BaseAdapter.BaseViewHolder<MODEL>> :
    PagingDataAdapter<MODEL, VH>(DiffUtilCallback<MODEL>()) {

    var onClickCallback: ((MODEL) -> Unit)? = null

    fun onItemClickListener(onClickCallback: (MODEL) -> Unit) {
        this.onClickCallback = onClickCallback
    }

    abstract class BaseViewHolder<MODEL : Any>(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        open fun onBind(item: MODEL, onClickCallback: ((MODEL) -> Unit)?) {}
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let { holder.onBind(it, onClickCallback) }
    }

}