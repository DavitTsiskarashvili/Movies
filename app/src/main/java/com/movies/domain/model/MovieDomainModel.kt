package com.movies.domain.model

data class MovieDomainModel(
    val id: Int,
    val title: String,
    val rating: Double,
    val releaseDate: String,
    val poster: String,
    val overview: String,
    var isFavourite: Boolean
)