package com.movies.presentation.base.data.model

data class MovieUIModel(
    val id: Int,
    val title: String,
    val rating: Double,
    val releaseDate: String,
    val poster: String,
    val overview: String,
    val duration: String,
    val isFavourite: Boolean,
    val genreString: String? = null,
)