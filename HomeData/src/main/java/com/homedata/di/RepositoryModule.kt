package com.homedata.di

import com.homedata.repository.MoviesRepositoryImpl
import com.homedata.repository.SearchRepositoryImpl
import com.homedomain.repository.MoviesRepository
import com.homedomain.repository.SearchRepository
import org.koin.dsl.module

val homeRepositoryModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(
            fetchMovies = get(),
            movieListDTOMapper = get(),
            genresDTOMapper = get(),
        )
    }
    single<SearchRepository> {
        SearchRepositoryImpl(
            fetchSearchedMovies = get(),
            movieListDTOMapper = get(),
        )
    }
}