package com.movies.presentation.base.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieUIModel(
    val id: Int,
    val title: String,
    val rating: Double,
    val releaseDate: String,
    val poster: String,
    val overview: String,
    val duration: String,
    var isFavourite: Boolean,
    val genreString: String? = null,
):Parcelable