package com.homedomain.di

import com.homedomain.usecase.movies.FetchGenresUseCase
import com.homedomain.usecase.movies.FetchMoviesUseCase
import com.homedomain.usecase.search.SearchMoviesUseCase
import org.koin.dsl.module

val homeUseCaseModule = module {
    single { FetchMoviesUseCase(moviesRepository = get()) }
    single { SearchMoviesUseCase(searchRepository = get()) }
    single { FetchGenresUseCase(moviesRepository = get()) }
}