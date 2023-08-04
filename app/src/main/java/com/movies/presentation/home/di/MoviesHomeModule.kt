package com.movies.presentation.home.di

import com.movies.presentation.home.ui.mapper.MovieDomainToUIMapper
import com.movies.presentation.home.ui.mapper.MovieUIToDomainMapper
import com.movies.presentation.home.view_model.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeMapperModule = module {
    single { MovieDomainToUIMapper() }
    single { MovieUIToDomainMapper() }
}

val homeViewModelModule = module {
    viewModel {
        HomeViewModel(
            moviesUseCase = get(),
            moviesUIMapper = get(),
            searchMoviesUseCase = get(),
            movieUIToDomain = get(),
            updateMovieStatus = get(),
            getFavouriteMovies = get(),
        )
    }
}