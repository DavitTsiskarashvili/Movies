package com.movies.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.movies.common.network.CategoryType
import com.movies.data.mapper.dto.GenresDTOMapper
import com.movies.data.mapper.dto.MovieListDTOMapper
import com.movies.data.remote.paging.MoviesPagingSource
import com.movies.data.remote.service.api.ServiceApi
import com.movies.data.remote.service.result_handler.retrofit.apiDataFetcher
import com.movies.domain.model.GenreDomainModel
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val fetchMovies: ServiceApi,
    private val movieListDTOMapper: MovieListDTOMapper,
    private val genresDTOMapper: GenresDTOMapper
) : MoviesRepository {
    override suspend fun fetchMovies(category: CategoryType): Pager<Int, MovieDomainModel> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                MoviesPagingSource(
                    fetchMovies,
                    movieListDTOMapper,
                    category = category.value
                )
            }
        )
    }

    override suspend fun fetchMovieGenre(): List<GenreDomainModel> {
        return genresDTOMapper(apiDataFetcher { fetchMovies.getMovieGenre() })
    }
}