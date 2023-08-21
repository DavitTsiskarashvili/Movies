package com.detailsdata.repository

import com.commondata.retrofit.apiDataFetcher
import com.detailsdata.mapper.dto.MovieDetailsDTOMapper
import com.detailsdata.remote.service.DetailsServiceApi
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val fetchMovies: DetailsServiceApi,
    private val movieDetailsDTOMapper: MovieDetailsDTOMapper
) : MoviesRepository {
    override suspend fun fetchMovieDetails(id: Int): MovieDomainModel {
        return movieDetailsDTOMapper(apiDataFetcher { fetchMovies.getMoviesDetails(id) })
    }
}