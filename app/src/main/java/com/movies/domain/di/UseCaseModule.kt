package com.movies.domain.di

import com.movies.domain.usecase.favourites.CheckFavouriteStatusUseCase
import com.movies.domain.usecase.favourites.GetFavouriteMoviesUseCase
import com.movies.domain.usecase.favourites.UpdateFavouriteStatusMovieUseCase
import com.movies.domain.usecase.movies.GetMoviesUseCase
import com.movies.domain.usecase.search.SearchMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetMoviesUseCase(moviesRepository = get()) }
    single { SearchMoviesUseCase(searchRepository = get()) }
    single { UpdateFavouriteStatusMovieUseCase(favouriteMovieRepository = get()) }
    single { GetFavouriteMoviesUseCase(favouriteMovieRepository = get()) }
    single { CheckFavouriteStatusUseCase(favouriteMovieRepository = get()) }
}