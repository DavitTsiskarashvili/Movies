package com.movies.domain.di

import com.movies.data.repository.FavouriteMovieRepositoryImpl
import com.detailsdata.repository.MoviesRepositoryImpl
import com.homedata.repository.SearchRepositoryImpl
import com.movies.domain.repository.FavouriteMovieRepository
import com.movies.domain.repository.MoviesRepository
import com.movies.domain.repository.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(
            fetchMovies = get(),
            movieListDTOMapper = get(),
            genresDTOMapper = get(),
            movieDetailsDTOMapper = get()
        )
    }
    single<SearchRepository> {
        SearchRepositoryImpl(
            fetchSearchedMovies = get(),
            movieListDTOMapper = get(),
        )
    }
    single<FavouriteMovieRepository> {
        FavouriteMovieRepositoryImpl(
            favouriteMoviesDao = get(),
            favouriteMovieEntityMapper = get(),
            favouriteMovieDomainMapper = get()
        )
    }
}