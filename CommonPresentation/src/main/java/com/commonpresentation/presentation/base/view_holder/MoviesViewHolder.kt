package com.commonpresentation.presentation.base.view_holder

import androidx.recyclerview.widget.RecyclerView
import com.commonpresentation.databinding.MovieItemBinding
import com.commonpresentation.extensions.loadImage
import com.commonpresentation.presentation.base.data.model.MovieUIModel

class MoviesViewHolder(
    private val binding: MovieItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieUIModel) {
        with(binding) {
            posterImageView.loadImage(item.poster)
            titleTextView.text = item.title
            releaseYearTextView.text = item.releaseDate
            ratingTextView.text = item.rating.toString()
            genreTextView.text = item.genreString
            favouritesToggleButton.isChecked = item.isFavourite
        }
    }
}