package com.movies.presentation.base.adapter.base

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.movies.presentation.base.adapter.diff_util.DiffUtilCallback

abstract class BaseAdapter<MODEL : Any, VH : BaseAdapter.BaseViewHolder<MODEL>> :
    ListAdapter<MODEL, VH>(DiffUtilCallback<MODEL>()) {

    var onClickCallback: ((MODEL) -> Unit)? = null

    fun onItemClickListener(onClickCallback: (MODEL) -> Unit) {
        this.onClickCallback = onClickCallback
    }

    abstract class BaseViewHolder<MODEL : Any>(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        open fun onBind(item: MODEL, onClickCallback: ((MODEL) -> Unit)?) {}

        open fun onBind2(
            item: MODEL,
            index: Int
        ){}
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position), onClickCallback)
    }

}