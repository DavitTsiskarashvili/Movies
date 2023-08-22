package com.homedata.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.commondata.retrofit.apiDataFetcher
import com.commondomain.model.MovieDomainModel
import com.homedata.mapper.dto.GenresDTOMapper
import com.homedata.mapper.dto.MovieListDTOMapper
import com.homedata.remote.paging.MoviesPagingSource
import com.homedata.remote.service.ServiceApi
import com.homedomain.model.GenreDomainModel
import com.homedomain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val fetchMovies: ServiceApi,
    private val movieListDTOMapper: MovieListDTOMapper,
    private val genresDTOMapper: GenresDTOMapper,
) : MoviesRepository {
    override suspend fun fetchMovies(category: String): Pager<Int, MovieDomainModel> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                MoviesPagingSource(
                    fetchMovies,
                    movieListDTOMapper,
                    category = category
                )
            }
        )
    }

    override suspend fun fetchMovieGenre(): List<GenreDomainModel> {
        return genresDTOMapper(apiDataFetcher { fetchMovies.getMovieGenre() })
    }

}