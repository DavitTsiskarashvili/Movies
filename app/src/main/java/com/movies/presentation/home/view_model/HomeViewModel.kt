package com.movies.presentation.home.view_model

import com.movies.common.extensions.viewModelScope
import com.movies.common.network.Category
import com.movies.common.utils.LiveDataDelegate
import com.movies.domain.usecase.movies.GetMoviesUseCase
import com.movies.presentation.base.view_model.BaseViewModel
import com.movies.presentation.model.mapper.MovieUIMapper
import com.movies.presentation.model.movie.MovieUIModel

class HomeViewModel(
    private val moviesUseCase: GetMoviesUseCase,
    private val moviesUIMapper: MovieUIMapper
) : BaseViewModel() {

    val fetchMoviesLiveData by LiveDataDelegate<List<MovieUIModel>>()
    val loadingLiveData by LiveDataDelegate<Boolean>()

    fun getMovies(category: Category){
        viewModelScope {
            loadingLiveData.addValue(true)
            fetchMoviesLiveData.addValue(moviesUIMapper.mapList(moviesUseCase.invoke(category)))
            loadingLiveData.addValue(false)
        }
    }

}