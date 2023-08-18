package com.movies.presentation.details.view_model

import com.movies.common.extensions.viewModelScope
import com.movies.domain.usecase.favourites.UpdateFavouriteStatusMovieUseCase
import com.movies.presentation.base.data.model.MovieUIModel
import com.movies.presentation.base.view_model.BaseViewModel
import com.movies.presentation.home.ui.mapper.movie.MovieUIToDomainMapper

class DetailsViewModel(
    private val updateMovieStatus: UpdateFavouriteStatusMovieUseCase,
    private val movieUIToDomain: MovieUIToDomainMapper
    ) : BaseViewModel<MovieUIModel>() {

    fun updateFavouriteMovieStatus(movie: MovieUIModel){
        viewModelScope {
            updateMovieStatus.invoke(movieUIToDomain(movie))
        }
    }

}