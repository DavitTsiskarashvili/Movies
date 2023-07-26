package com.movies.domain.repository

import com.movies.domain.model.MovieDomainModel

interface SearchRepository {

    suspend fun searchMovies(query: String): List<MovieDomainModel>
}