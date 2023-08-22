package com.example.featurefavouritesimpl.favourite.vm

import androidx.lifecycle.MutableLiveData
import com.commonpresentation.extensions.viewModelScope
import com.commonpresentation.mapper.movie.MovieDomainToUIMapper
import com.commonpresentation.mapper.movie.MovieUIToDomainMapper
import com.commonpresentation.presentation.base.data.model.MovieUIModel
import com.commonpresentation.presentation.base.data.ui_state.UIState
import com.commonpresentation.presentation.base.view_model.BaseViewModel
import com.favouritesdomain.usecase.FetchFavouriteMoviesUseCase
import com.favouritesdomain.usecase.UpdateFavouriteStatusMovieUseCase

class FavouriteViewModel(
    private val getFavouriteMovies: FetchFavouriteMoviesUseCase,
    private val movieUIToDomain: MovieUIToDomainMapper,
    private val moviesDomainToUIMapper: MovieDomainToUIMapper,
    private val updateMovieStatus: UpdateFavouriteStatusMovieUseCase,
) : BaseViewModel<List<MovieUIModel>>() {

    val moviesLiveData: MutableLiveData<MovieUIModel> = MutableLiveData()

    override fun onCreate() {
        viewModelScope {
            fetchFavouriteMovies()
        }
    }

    suspend fun fetchFavouriteMovies() {
        _uiStateLiveData.postValue(
            UIState.Success(moviesDomainToUIMapper.mapList(getFavouriteMovies.invoke()))
        )
    }

    fun updateFavouriteMovieStatus(movie: MovieUIModel) {
        viewModelScope {
            updateMovieStatus.invoke(movieUIToDomain(movie))
            fetchFavouriteMovies()
        }
    }

}