package com.movies.presentation.home.di

import com.movies.presentation.details.view_model.DetailsViewModel
import com.movies.presentation.home.view_model.HomeViewModel
import com.movies.presentation.view.loader.LoaderDialog
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
            getFavouriteMovies = get()
        )
    }

    viewModel {
        DetailsViewModel(
            updateMovieStatus = get(),
            movieUIToDomain = get()
        )
    }

    single { LoaderDialog(context = get()) }

}