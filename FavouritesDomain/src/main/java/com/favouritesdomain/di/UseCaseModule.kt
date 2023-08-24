package com.favouritesdomain.di

import com.favouritesdomain.usecase.CheckFavouriteStatusUseCase
import com.favouritesdomain.usecase.FetchFavouriteMoviesUseCase
import com.favouritesdomain.usecase.UpdateFavouriteStatusMovieUseCase
import org.koin.dsl.module

val favouritesUseCaseModule = module {
    single { UpdateFavouriteStatusMovieUseCase(favouriteMovieRepository = get()) }
    single { FetchFavouriteMoviesUseCase(favouriteMovieRepository = get()) }
    single { CheckFavouriteStatusUseCase(favouriteMovieRepository = get()) }
}