package com.favouritesdata.di

import com.favouritesdata.repository.FavouriteMovieRepositoryImpl
import com.favouritesdomain.repository.FavouriteMovieRepository
import org.koin.dsl.module

val favouritesRepositoryModule = module {
    single<FavouriteMovieRepository> {
        FavouriteMovieRepositoryImpl(
            favouriteMoviesDao = get(),
            favouriteMovieEntityMapper = get(),
            favouriteMovieDomainMapper = get()
        )
    }
}