package com.movies.domain.repository

import com.movies.domain.model.MovieDomainModel

interface FavouriteMovieRepository {
    suspend fun updateFavouriteMovieStatus(movie: MovieDomainModel)

    suspend fun getFavouriteMovies(): List<MovieDomainModel>
}