package com.movies.presentation.favourite.vm

import com.movies.common.extensions.viewModelScope
import com.movies.domain.usecase.favourites.GetFavouriteMoviesUseCase
import com.movies.domain.usecase.favourites.UpdateFavouriteStatusMovieUseCase
import com.movies.presentation.base.data.model.MovieUIModel
import com.movies.presentation.base.data.ui_state.UIState
import com.movies.presentation.base.view_model.BaseViewModel
import com.movies.presentation.home.ui.mapper.movie.MovieDomainToUIMapper
import com.movies.presentation.home.ui.mapper.movie.MovieUIToDomainMapper

class FavouriteViewModel(
    private val getFavouriteMovies: GetFavouriteMoviesUseCase,
    private val movieUIToDomain: MovieUIToDomainMapper,
    private val moviesUIMapper: MovieDomainToUIMapper,
    private val updateMovieStatus: UpdateFavouriteStatusMovieUseCase,
) : BaseViewModel<List<MovieUIModel>>() {

    override fun onCreate() {
        fetchFavouriteMovies()
    }

    fun fetchFavouriteMovies() {
        viewModelScope {
            _uiStateLiveData.postValue(
                UIState.Success(moviesUIMapper.mapList(getFavouriteMovies.invoke()))
            )
        }
    }

    fun updateFavouriteMovieStatus(movie: MovieUIModel, callback: (() -> Unit)? = null) {
        viewModelScope {
            updateMovieStatus.invoke(movieUIToDomain(movie))
            callback?.invoke()
        }
    }

}