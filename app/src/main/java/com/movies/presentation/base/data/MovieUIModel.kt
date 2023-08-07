package com.movies.presentation.base.data

import android.os.Parcelable
import com.movies.presentation.home.ui.ui_state.BaseUiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieUIModel(
    val id: Int,
    val title: String,
    val rating: Double,
    val releaseDate: String,
    val poster: String,
    val overview: String,
    val isFavourite: Boolean
) : Parcelable, BaseUiState