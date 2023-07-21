package com.movies.presentation.base.adapter

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<MODEL : Any, VH:BaseAdapter.BaseViewHolder<MODEL>> :
    ListAdapter<MODEL, VH>(DiffUtilCallback<MODEL>()) {

    var onClickCallback: ((MODEL) -> Unit)? = null


    fun onItemClickListener(onClickCallback: (MODEL) -> Unit) {
        this.onClickCallback = onClickCallback
    }

    abstract class BaseViewHolder<MODEL : Any>(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        open fun onBind(item: MODEL, onClickCallback: ((MODEL) -> Unit)?){

        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position), onClickCallback)
    }

}