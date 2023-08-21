package com.commonpresentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.commonpresentation.presentation.base.data.model.MovieUIModel

class DiffUtilCallback : DiffUtil.ItemCallback<MovieUIModel>() {
    override fun areItemsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean {
        return oldItem == newItem
    }
}