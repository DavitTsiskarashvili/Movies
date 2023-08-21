package com.favouritesdata.repository

import com.favouritesdata.mapper.FavouriteMovieDomainToEntityMapper
import com.favouritesdata.mapper.FavouriteMovieEntityToDomainMapper
import com.favouritesdata.local.dao.FavouriteMoviesDao
import com.movies.domain.model.MovieDomainModel
import com.favouritesdomain.repository.FavouriteMovieRepository

class FavouriteMovieRepositoryImpl(
    private val favouriteMoviesDao: FavouriteMoviesDao,
    private val favouriteMovieEntityMapper: FavouriteMovieEntityToDomainMapper,
    private val favouriteMovieDomainMapper: FavouriteMovieDomainToEntityMapper
) : FavouriteMovieRepository {
    override suspend fun insertFavouriteMovie(movie: MovieDomainModel) {
        return favouriteMoviesDao.insertFavouriteMovie(favouriteMovieDomainMapper(movie))
    }

    override suspend fun deleteFavouriteMovie(movie: MovieDomainModel) {
        return favouriteMoviesDao.deleteFavouriteMovie(favouriteMovieDomainMapper(movie))
    }

    override suspend fun getFavouriteMovies(): List<MovieDomainModel> {
        return favouriteMoviesDao.getFavouriteMovies().map { favouriteMovieEntityMapper(it) }
    }

    override suspend fun isFavouriteMovie(id: Int): Boolean {
        return favouriteMoviesDao.isFavouriteMovie(id)
    }
}