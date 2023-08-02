package com.movies.domain.repository

import androidx.paging.PagingData
import com.movies.common.network.CategoryType
import com.movies.domain.model.MovieDomainModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun fetchMovies(category: CategoryType): Flow<PagingData<MovieDomainModel>>
}