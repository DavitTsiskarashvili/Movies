package com.example.featurefavouritesimpl.favourite.di

import com.example.featurefavouritesimpl.favourite.vm.FavouriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouriteViewModelModule = module {
    viewModel {
        FavouriteViewModel(
            moviesDomainToUIMapper = get(),
            getFavouriteMovies = get(),
            updateMovieStatus = get(),
            movieUIToDomain = get()
        )
    }
}