package com.movies.presentation.base.data.ui_state

sealed class UIState<out T> {
    data class Success<out T>(val data: T) : UIState<T>()
    data class Error(val throwable: Throwable): UIState<Nothing>()
    object Loading: UIState<Nothing>()
}
