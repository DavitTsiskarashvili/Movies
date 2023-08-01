package com.movies.domain.repository

import androidx.paging.PagingData
import com.movies.common.network.CategoryType
import com.movies.common.utils.LiveDataDelegate
import com.movies.domain.model.MovieDomainModel

interface MoviesRepository {
    suspend fun fetchMovies(category: CategoryType): LiveDataDelegate<PagingData<MovieDomainModel>>
}