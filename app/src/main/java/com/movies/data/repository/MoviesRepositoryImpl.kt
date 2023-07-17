package com.movies.data.repository

import com.movies.data.remote.mapper.MovieListDTOMapper
import com.movies.data.remote.service.api.ServiceApi
import com.movies.data.remote.service.result_handler.resource.Resource
import com.movies.data.remote.service.result_handler.retrofit.apiDataFetcher
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val fetchMovies: ServiceApi,
    private val movieListDTOMapper: MovieListDTOMapper
): MoviesRepository {
    override suspend fun getMoviesFromNetwork(): List<MovieDomainModel> {
        val remoteData = apiDataFetcher { fetchMovies.getMovies() }
        if (remoteData is Resource.Success){
            return movieListDTOMapper(remoteData.data)
        }
        return emptyList()
    }

}