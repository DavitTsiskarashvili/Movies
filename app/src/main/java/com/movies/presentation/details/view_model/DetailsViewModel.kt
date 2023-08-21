package com.movies.presentation.details.view_model

import com.movies.common.extensions.viewModelScope
import com.favouritesdomain.usecase.CheckFavouriteStatusUseCase
import com.favouritesdomain.usecase.UpdateFavouriteStatusMovieUseCase
import com.detailsdomain.usecase.FetchMovieDetailsUseCase
import com.movies.presentation.base.data.model.MovieUIModel
import com.movies.presentation.base.data.ui_state.UIState
import com.movies.presentation.base.view_model.BaseViewModel
import com.movies.presentation.details.ui.ui_state.DetailsUIState
import com.movies.presentation.home.ui.mapper.movie.MovieDomainToUIMapper
import com.movies.presentation.home.ui.mapper.movie.MovieUIToDomainMapper

class DetailsViewModel(
    private val updateMovieStatus: UpdateFavouriteStatusMovieUseCase,
    private val movieUIToDomain: MovieUIToDomainMapper,
    private val movieDomainToUIMapper: MovieDomainToUIMapper,
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase,
    private val checkFavouriteStatusUseCase: CheckFavouriteStatusUseCase
) : BaseViewModel<DetailsUIState>() {

    fun fetchMovieDetails(movieId: Int) {
        launchNetwork {
            loading { if (it) _uiStateLiveData.postValue(UIState.Loading) }

            executeApi { fetchMovieDetailsUseCase.invoke(movieId) }

            success { handleSuccessCase(it) }

            error { _uiStateLiveData.postValue(UIState.Error(it)) }
        }
    }

    private fun handleSuccessCase(movie: MovieDomainModel) {
        viewModelScope {
            movie.isFavourite = checkFavouriteStatusUseCase(movie.id)
            _uiStateLiveData.postValue(UIState.Success(data = DetailsUIState(movieDomainToUIMapper(movie))))
        }
    }

    fun updateFavouriteMovieStatus(movie: MovieUIModel) {
        viewModelScope {
            updateMovieStatus.invoke(movieUIToDomain(movie))
        }
    }

}