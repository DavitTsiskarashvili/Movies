package com.movies.presentation.favourite.di

import com.movies.presentation.favourite.vm.FavouriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouriteViewModelModule = module {
    viewModel {
        FavouriteViewModel(
            moviesUIMapper = get(),
            getFavouriteMovies = get(),
            updateMovieStatus = get(),
            movieUIToDomain = get()
        )
    }
}