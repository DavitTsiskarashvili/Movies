package com.movies.domain.repository

import androidx.paging.PagingData
import com.movies.domain.model.MovieDomainModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun searchMovies(query: String): Flow<PagingData<MovieDomainModel>>
}