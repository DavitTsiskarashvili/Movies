package com.detailsdata.repository

import com.commondata.retrofit.apiDataFetcher
import com.commondomain.model.MovieDomainModel
import com.detailsdata.mapper.dto.MovieDetailsDTOMapper
import com.detailsdata.remote.service.DetailsServiceApi
import com.detailsdomain.repository.MovieDetailsRepository

class MovieDetailsRepositoryImpl(
    private val fetchMovies: DetailsServiceApi,
    private val movieDetailsDTOMapper: MovieDetailsDTOMapper
) : MovieDetailsRepository {
    override suspend fun fetchMovieDetails(id: Int): MovieDomainModel {
        return movieDetailsDTOMapper(apiDataFetcher { fetchMovies.getMoviesDetails(id) })
    }
}