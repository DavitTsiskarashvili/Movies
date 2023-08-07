package com.movies.presentation.home.ui.ui_state

import android.util.Log
import com.movies.presentation.base.data.ui_state.UIState

interface UIStateHandler<in T> {
    fun handleUIState(uiState: UIState<T>) {
        Log.d("TAG",uiState.toString())
        when (uiState) {
            is UIState.Success -> {
                onDataLoaded(uiState.data)
                onLoading(false)
            }
            is UIState.Error -> {
                onError(uiState.throwable)
                onLoading(false)
            }
            is UIState.Loading -> {
                onLoading(true)
            }
        }
    }

    fun onDataLoaded(data: T)

    fun onLoading(loading: Boolean)

    fun onError(error: Throwable)
}