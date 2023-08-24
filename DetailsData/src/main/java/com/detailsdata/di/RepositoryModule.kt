package com.detailsdata.di

import com.detailsdata.repository.MovieDetailsRepositoryImpl
import com.detailsdomain.repository.MovieDetailsRepository
import org.koin.dsl.module

val detailsRepositoryModule = module {
    single<MovieDetailsRepository> {
        MovieDetailsRepositoryImpl(
            fetchMovies = get(),
            movieDetailsDTOMapper = get()
        )
    }
}