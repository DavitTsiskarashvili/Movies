package com.detailsdomain.di

import com.detailsdomain.usecase.FetchMovieDetailsUseCase
import org.koin.dsl.module

val detailsUseCaseModule = module {
    single { FetchMovieDetailsUseCase(moviesRepository = get()) }
}