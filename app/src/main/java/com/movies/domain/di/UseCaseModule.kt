package com.movies.domain.di

import com.movies.domain.usecase.movies.GetMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetMoviesUseCase (moviesRepository = get()) }
}