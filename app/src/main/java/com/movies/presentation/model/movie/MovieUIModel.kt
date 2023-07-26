package com.movies.presentation.model.movie

data class MovieUIModel(
    val id: Int,
    val title: String,
    val rating: Double,
    val releaseDate: String,
    val poster: String,
    val overview: String,
    val isFavourite: Boolean
)