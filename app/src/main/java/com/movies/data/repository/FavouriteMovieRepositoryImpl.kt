package com.movies.data.repository

import com.movies.data.local.dao.FavouriteMoviesDao
import com.movies.data.mapper.entity.FavouriteMovieDomainToEntityMapper
import com.movies.data.mapper.entity.FavouriteMovieEntityToDomainMapper
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.FavouriteMovieRepository

class FavouriteMovieRepositoryImpl(
    private val favouriteMoviesDao: FavouriteMoviesDao,
    private val favouriteMovieEntityMapper: FavouriteMovieEntityToDomainMapper,
    private val favouriteMovieDomainMapper: FavouriteMovieDomainToEntityMapper
) : FavouriteMovieRepository {
    override suspend fun insertFavouriteMovie(movie: MovieDomainModel) {
        favouriteMoviesDao.insertFavouriteMovie(favouriteMovieDomainMapper(movie))
    }

    override suspend fun deleteFavouriteMovie(movie: MovieDomainModel) {
        favouriteMoviesDao.deleteFavouriteMovie(favouriteMovieDomainMapper(movie))
    }

    override suspend fun getFavouriteMovies(): List<MovieDomainModel> {
        return favouriteMoviesDao.getFavouriteMovies().map { favouriteMovieEntityMapper(it) }
    }
}