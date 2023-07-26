package com.movies.domain.di

import com.movies.data.repository.MoviesRepositoryImpl
import com.movies.data.repository.SearchRepositoryImpl
import com.movies.domain.repository.MoviesRepository
import com.movies.domain.repository.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(
            fetchMovies = get(),
            movieListDTOMapper = get()
        )
    }
    single<SearchRepository> {
        SearchRepositoryImpl(
            fetchSearchedMovies = get(),
            movieListDTOMapper = get()
        )
    }
}