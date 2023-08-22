package com.homedata.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.commondomain.model.MovieDomainModel
import com.homedata.mapper.dto.MovieListDTOMapper
import com.homedata.remote.paging.MoviesPagingSource
import com.homedata.remote.service.ServiceApi
import com.homedomain.repository.SearchRepository

class SearchRepositoryImpl(
    private val fetchSearchedMovies: ServiceApi,
    private val movieListDTOMapper: MovieListDTOMapper,
) : SearchRepository {
    override suspend fun searchMovies(query: String): Pager<Int, MovieDomainModel> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                MoviesPagingSource(
                    fetchSearchedMovies,
                    movieListDTOMapper,
                    search = query,
                )
            }
        )
    }
}