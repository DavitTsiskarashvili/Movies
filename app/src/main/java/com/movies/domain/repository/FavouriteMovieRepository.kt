package com.movies.domain.repository

import com.movies.domain.model.MovieDomainModel

interface FavouriteMovieRepository {
    suspend fun insertFavouriteMovie(movie: MovieDomainModel)

    suspend fun deleteFavouriteMovie(movie: MovieDomainModel)

    suspend fun getFavouriteMovies(): List<MovieDomainModel>

    suspend fun isFavouriteMovie (): List<MovieDomainModel>

}