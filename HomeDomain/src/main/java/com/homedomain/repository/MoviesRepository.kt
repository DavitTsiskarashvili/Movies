package com.homedomain.repository

import androidx.paging.Pager
import com.commondomain.model.MovieDomainModel
import com.homedomain.model.GenreDomainModel

interface MoviesRepository {
    suspend fun fetchMovies(category: String): Pager<Int, MovieDomainModel>
    suspend fun fetchMovieGenre(): List<GenreDomainModel>
}