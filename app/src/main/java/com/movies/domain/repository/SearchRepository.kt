package com.movies.domain.repository

import androidx.paging.Pager
import com.movies.domain.model.MovieDomainModel

interface SearchRepository {
    suspend fun searchMovies(query: String): Pager<Int, MovieDomainModel>
}