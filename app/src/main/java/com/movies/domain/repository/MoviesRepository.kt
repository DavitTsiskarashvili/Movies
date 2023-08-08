package com.movies.domain.repository

import androidx.paging.Pager
import com.movies.common.network.CategoryType
import com.movies.domain.model.GenreDomainModel
import com.movies.domain.model.MovieDomainModel

interface MoviesRepository {
    suspend fun fetchMovies(category: CategoryType): Pager<Int, MovieDomainModel>
    suspend fun fetchMovieGenre(): List<GenreDomainModel>
    suspend fun fetchMovieDetails(id: Int): MovieDomainModel
}