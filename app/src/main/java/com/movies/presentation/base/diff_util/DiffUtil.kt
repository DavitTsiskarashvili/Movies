package com.movies.presentation.base.diff_util

import androidx.recyclerview.widget.DiffUtil
import com.movies.presentation.base.data.model.MovieUIModel

class DiffUtilCallback : DiffUtil.ItemCallback<MovieUIModel>() {
    override fun areItemsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean {
        return oldItem == newItem
    }
}