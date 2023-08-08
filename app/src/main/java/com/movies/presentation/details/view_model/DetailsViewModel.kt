package com.movies.presentation.details.view_model

import com.movies.common.extensions.viewModelScope
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.usecase.favourites.CheckFavouriteStatusUseCase
import com.movies.domain.usecase.favourites.UpdateFavouriteStatusMovieUseCase
import com.movies.domain.usecase.movies.GetMovieDetailsUseCase
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
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val checkFavouriteStatusUseCase: CheckFavouriteStatusUseCase
) : BaseViewModel<DetailsUIState>() {


    fun fetchMovieDetails(movieId: Int) {
        launchNetwork<MovieDomainModel> {
            loading {
                if (it) {
                    _uiStateFlow.emit(UIState.Loading)
                }
            }
            executeApi {
                getMovieDetailsUseCase.invoke(movieId)
            }
            success {
                it.isFavourite = checkFavouriteStatusUseCase(it.id)
                _uiStateFlow.emit(UIState.Success(data = DetailsUIState(movieDomainToUIMapper(it))))
            }
            error {
                _uiStateFlow.emit(UIState.Error(it))
            }
        }
    }


    fun updateFavouriteMovieStatus(movie: MovieUIModel) {
        viewModelScope {
            updateMovieStatus.invoke(movieUIToDomain(movie))
        }
    }

}