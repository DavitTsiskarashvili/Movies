package com.movies.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.movies.common.network.CategoryType
import com.movies.data.local.dao.FavouriteMoviesDao
import com.movies.data.remote.mapper.MovieListDTOMapper
import com.movies.data.remote.paging.MoviesPagingSource
import com.movies.data.remote.service.api.ServiceApi
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(
    private val fetchMovies: ServiceApi,
    private val movieListDTOMapper: MovieListDTOMapper,
    private val favouriteMoviesDao: FavouriteMoviesDao,
) : MoviesRepository {
    override suspend fun fetchMovies(category: CategoryType): Flow<PagingData<MovieDomainModel>> {
        return Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(fetchMovies, category.value, movieListDTOMapper) }
        ).flow
    }

}