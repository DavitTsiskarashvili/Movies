package com.movies.presentation.details.view_model

import com.movies.common.extensions.viewModelScope
import com.movies.domain.usecase.movies.UpdateFavouriteStatusMovieUseCase
import com.movies.presentation.base.view_model.BaseViewModel
import com.movies.presentation.model.mapper.MovieUIToDomainMapper
import com.movies.presentation.model.movie.MovieUIModel

class DetailsViewModel(
    private val updateMovieStatus: UpdateFavouriteStatusMovieUseCase,
    private val movieUIToDomain: MovieUIToDomainMapper
    ) : BaseViewModel() {

    fun updateFavouriteMovieStatus(movie: MovieUIModel){
        viewModelScope {
            updateMovieStatus.invoke(movieUIToDomain(movie))
        }
    }

}