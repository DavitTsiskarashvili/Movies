package com.movies.presentation.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.movies.common.extensions.loadImage
import com.movies.databinding.MovieItemBinding
import com.movies.presentation.base.data.model.MovieUIModel
import com.movies.presentation.base.diff_util.DiffUtilCallback

class MoviePagingAdapter :
    PagingDataAdapter<MovieUIModel, MoviePagingAdapter.MoviesViewHolder>(DiffUtilCallback()) {

    private var onClickCallback: ((MovieUIModel) -> Unit)? = null
    private var onFavouriteClick: ((MovieUIModel, Boolean) -> Unit)? = null

    fun onItemClickListener(onClickCallback: (MovieUIModel) -> Unit) {
        this.onClickCallback = onClickCallback
    }

    fun onFavouriteClickListener(onFavouriteClick: (MovieUIModel, Boolean) -> Unit) {
        this.onFavouriteClick = onFavouriteClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MoviesViewHolder(binding).apply {
            binding.favouritesToggleButton.setOnClickListener {
                val currentItem = getItem(bindingAdapterPosition)
                currentItem?.let {
                    val newIsFavourite = !it.isFavourite
                    onFavouriteClick?.invoke(it, newIsFavourite)
                    it.isFavourite = newIsFavourite
                    notifyItemChanged(bindingAdapterPosition)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onClickCallback) }
    }

    class MoviesViewHolder(private val binding: MovieItemBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: MovieUIModel,
            onClickCallback: ((MovieUIModel) -> Unit)?,
        ) {
            with(item) {
                with(binding) {
                    posterImageView.loadImage(poster)
                    titleTextView.text = title
                    releaseYearTextView.text = releaseDate
                    ratingTextView.text = rating.toString()
                    genreTextView.text = genreString
                    favouritesToggleButton.isChecked = isFavourite

                    root.setOnClickListener {
                        onClickCallback?.invoke(item)
                    }
                }
            }
        }
    }

}