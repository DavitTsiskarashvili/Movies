package com.movies.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.movies.data.local.dao.FavouriteMoviesDao
import com.movies.data.remote.mapper.MovieListDTOMapper
import com.movies.data.remote.paging.MoviesPagingSource
import com.movies.data.remote.service.api.ServiceApi
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val fetchSearchedMovies: ServiceApi,
    private val movieListDTOMapper: MovieListDTOMapper,
    private val favouriteMoviesDao: FavouriteMoviesDao,
    ) : SearchRepository {
    override suspend fun searchMovies(query: String): Flow<PagingData<MovieDomainModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(fetchSearchedMovies, movieListDTOMapper, search = query) }
        ).flow
    }
}