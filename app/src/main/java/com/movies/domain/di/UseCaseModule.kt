package com.movies.domain.di

import com.movies.domain.usecase.favourites.CheckFavouriteStatusUseCase
import com.movies.domain.usecase.favourites.FetchFavouriteMoviesUseCase
import com.movies.domain.usecase.favourites.UpdateFavouriteStatusMovieUseCase
import com.movies.domain.usecase.movies.FetchGenresUseCase
import com.movies.domain.usecase.movies.FetchMovieDetailsUseCase
import com.movies.domain.usecase.movies.FetchMoviesUseCase
import com.movies.domain.usecase.search.SearchMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { FetchMoviesUseCase(moviesRepository = get()) }
    single { SearchMoviesUseCase(searchRepository = get()) }
    single { UpdateFavouriteStatusMovieUseCase(favouriteMovieRepository = get()) }
    single { FetchFavouriteMoviesUseCase(favouriteMovieRepository = get()) }
    single { CheckFavouriteStatusUseCase(favouriteMovieRepository = get()) }
    single { FetchGenresUseCase(moviesRepository = get()) }
    single { FetchMovieDetailsUseCase(moviesRepository = get()) }
}