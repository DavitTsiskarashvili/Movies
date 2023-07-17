package com.movies.domain.repository

import com.movies.domain.model.MoviesDomainModel

interface MoviesRepository {
    suspend fun getMoviesFromNetwork(): List<MoviesDomainModel>
}