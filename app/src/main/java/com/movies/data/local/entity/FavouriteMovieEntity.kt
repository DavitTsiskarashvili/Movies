package com.movies.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movie")
data class FavouriteMovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val rating: Double,
    val releaseDate: String,
    val poster: String,
    val overview: String,
    val genreString: String,
    val runtime: String,
)