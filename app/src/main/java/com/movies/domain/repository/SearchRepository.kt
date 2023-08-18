package com.movies.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.movies.domain.model.MovieDomainModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun searchMovies(query: String): Pager<Int, MovieDomainModel>
}