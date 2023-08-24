package com.favouritesdomain.repository

import com.commondomain.model.MovieDomainModel

interface FavouriteMovieRepository {
    suspend fun insertFavouriteMovie(movie: MovieDomainModel)

    suspend fun deleteFavouriteMovie(movie: MovieDomainModel)

    suspend fun getFavouriteMovies(): List<MovieDomainModel>

    suspend fun isFavouriteMovie (id: Int): Boolean
}