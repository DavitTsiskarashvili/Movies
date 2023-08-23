package com.example.featurehomeimpl.home.di

import com.example.featurehomeimpl.home.view_model.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeViewModelModule = module {
    viewModel {
        HomeViewModel(
            moviesUseCase = get(),
            moviesUIMapper = get(),
            searchMoviesUseCase = get(),
            checkFavouriteStatusUseCase = get(),
            genresUseCase = get(),
            movieUIToDomain = get(),
            updateMovieStatus = get(),
            favouritesNavigationApi = get(),
            detailsNavigationApi = get()
        )
    }
}