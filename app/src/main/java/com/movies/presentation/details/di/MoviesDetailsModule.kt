package com.movies.presentation.details.di

import com.movies.presentation.details.view_model.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
    viewModel {
        DetailsViewModel(
            updateMovieStatus = get(),
            movieUIToDomain = get()
        )
    }
}