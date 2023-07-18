package com.movies.presentation.home.view_model

import android.util.Log
import com.movies.common.extensions.viewModelScope
import com.movies.domain.usecase.movies.GetMoviesUseCase
import com.movies.presentation.base.view_model.BaseViewModel

class HomeViewModel(
    private val moviesUseCase: GetMoviesUseCase
) : BaseViewModel() {

    fun getMovies(){
        viewModelScope {
            moviesUseCase.invoke()
            Log.d("movielist", "${  moviesUseCase.invoke() } ")

        }
    }

}