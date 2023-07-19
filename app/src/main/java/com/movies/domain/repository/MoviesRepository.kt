package com.movies.domain.repository

import com.movies.common.network.Category
import com.movies.domain.model.MovieDomainModel

interface MoviesRepository {
    suspend fun fetchMovies(category: Category): List<MovieDomainModel>
}