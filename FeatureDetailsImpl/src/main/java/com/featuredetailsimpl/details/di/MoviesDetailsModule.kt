package com.featuredetailsimpl.details.di

import com.featuredetailsimpl.details.view_model.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsViewModelModule = module {
    viewModel {
        DetailsViewModel(
            updateMovieStatus = get(),
            movieUIToDomain = get(),
            movieDomainToUIMapper = get(),
            fetchMovieDetailsUseCase = get(),
            checkFavouriteStatusUseCase = get()
        )
    }
}