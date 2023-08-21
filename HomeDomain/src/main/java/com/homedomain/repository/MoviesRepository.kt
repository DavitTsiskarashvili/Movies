package com.homedomain.repository

import androidx.paging.Pager
import com.commondomain.model.MovieDomainModel
import com.homedomain.model.GenreDomainModel
import com.movies.common.network.CategoryType
import com.movies.domain.model.GenreDomainModel

interface MoviesRepository {
    suspend fun fetchMovies(category: CategoryType): Pager<Int, MovieDomainModel>
    suspend fun fetchMovieGenre(): List<GenreDomainModel>
}