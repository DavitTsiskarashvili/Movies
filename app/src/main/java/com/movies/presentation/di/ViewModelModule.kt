package com.movies.presentation.di

import com.movies.presentation.home.view_model.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(moviesUseCase = get(), moviesUIMapper = get()) }
}