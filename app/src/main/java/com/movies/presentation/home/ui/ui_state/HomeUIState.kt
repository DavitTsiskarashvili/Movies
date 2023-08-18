package com.movies.presentation.home.ui.ui_state

import androidx.paging.PagingData
import com.movies.presentation.base.data.MovieUIModel

data class HomeUIState(
    val pagingData: PagingData<MovieUIModel>? = null,
    val favouritesData: List<MovieUIModel>? = null
): BaseUiState

interface BaseUiState