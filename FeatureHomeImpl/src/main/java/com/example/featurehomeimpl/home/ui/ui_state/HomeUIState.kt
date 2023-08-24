package com.example.featurehomeimpl.home.ui.ui_state

import androidx.paging.PagingData
import com.commonpresentation.presentation.base.data.model.MovieUIModel

data class HomeUIState(
    val pagingData: PagingData<MovieUIModel>? = null,
    val favouritesData: List<MovieUIModel>? = null
)