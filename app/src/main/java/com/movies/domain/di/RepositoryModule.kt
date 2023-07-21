package com.movies.domain.di

import com.movies.data.repository.MoviesRepositoryImpl
import com.movies.domain.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(fetchMovies = get(), movieListDTOMapper = get()) }
}