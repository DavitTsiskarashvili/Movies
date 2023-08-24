package com.homedomain.repository

import androidx.paging.Pager
import com.commondomain.model.MovieDomainModel

interface SearchRepository {
    suspend fun searchMovies(query: String): Pager<Int, MovieDomainModel>
}