package com.commonpresentation.presentation.base.data.ui_state

interface UIStateHandler<in T> {
    fun handleUIState(uiState: UIState<T>) {
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