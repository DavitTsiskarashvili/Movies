package com.movies.domain.repository

import com.movies.common.network.CategoryType
import com.movies.domain.model.MovieDomainModel

interface MoviesRepository {
    suspend fun fetchMovies(category: CategoryType): List<MovieDomainModel>
}