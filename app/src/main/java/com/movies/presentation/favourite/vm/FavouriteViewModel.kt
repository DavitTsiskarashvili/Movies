package com.movies.presentation.favourite.vm

import androidx.lifecycle.MutableLiveData
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

    val moviesLiveData: MutableLiveData<MovieUIModel> = MutableLiveData()

    override fun onCreate() {
        viewModelScope {
            fetchFavouriteMovies()
        }
    }

    private suspend fun fetchFavouriteMovies() {
        _uiStateLiveData.postValue(
            UIState.Success(moviesUIMapper.mapList(getFavouriteMovies.invoke()))
        )
    }

    fun updateFavouriteMovieStatus(movie: MovieUIModel) {
        viewModelScope {
            updateMovieStatus.invoke(movieUIToDomain(movie))
            fetchFavouriteMovies()
        }
    }

}