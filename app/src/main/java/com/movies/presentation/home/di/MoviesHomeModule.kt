package com.movies.presentation.home.di

import com.movies.presentation.details.view_model.DetailsViewModel
import com.movies.presentation.home.view_model.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeViewModelModule = module {
    viewModel {
        HomeViewModel(
            moviesUseCase = get(),
            moviesUIMapper = get(),
            searchMoviesUseCase = get(),
            movieUIToDomain = get(),
            updateMovieStatus = get(),
            getFavouriteMovies = get(),
            checkFavouriteStatusUseCase = get(),
            genresUseCase = get()
        )
    }

    viewModel {
        DetailsViewModel(
            updateMovieStatus = get(),
            movieUIToDomain = get(),
            movieDomainToUIMapper = get(),
            getMovieDetailsUseCase = get(),
            checkFavouriteStatusUseCase = get()
        )
    }

}