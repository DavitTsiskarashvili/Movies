package com.movies.domain.repository

import com.movies.domain.model.MovieDomainModel

interface MoviesRepository {
    suspend fun getMoviesFromNetwork(): List<MovieDomainModel>
}