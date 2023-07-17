package com.movies.data.repository

import com.movies.data.remote.service.api.ServiceApi
import com.movies.data.remote.service.result_handler.resource.Resource
import com.movies.data.remote.service.result_handler.retrofit.apiDataFetcher
import com.movies.domain.model.MoviesDomainModel
import com.movies.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val fetchMovies: ServiceApi,
): MoviesRepository {
    override suspend fun getMoviesFromNetwork(): List<MoviesDomainModel> {
        val remoteData = apiDataFetcher { fetchMovies.getMovies() }
        if (remoteData is Resource.Success){

        }
    }



}