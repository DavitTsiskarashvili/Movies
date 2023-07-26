package com.movies.domain.di

import com.movies.domain.usecase.movies.GetFavouriteMoviesUseCase
import com.movies.domain.usecase.movies.GetMoviesUseCase
import com.movies.domain.usecase.movies.UpdateFavouriteStatusMovieUseCase
import com.movies.domain.usecase.search.SearchMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetMoviesUseCase(moviesRepository = get()) }
    single { SearchMoviesUseCase(searchRepository = get()) }
    single { UpdateFavouriteStatusMovieUseCase(favouriteMovieRepository = get()) }
    single { GetFavouriteMoviesUseCase(favouriteMovieRepository = get()) }
}