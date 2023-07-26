package com.movies.data.repository

import com.movies.data.remote.mapper.MovieListDTOMapper
import com.movies.data.remote.service.api.ServiceApi
import com.movies.data.remote.service.result_handler.resource.Resource
import com.movies.data.remote.service.result_handler.retrofit.apiDataFetcher
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val fetchSearchedMovies: ServiceApi,
    private val movieListDTOMapper: MovieListDTOMapper
) : SearchRepository {
    override suspend fun searchMovies(query: String): List<MovieDomainModel> {
        val remoteData = apiDataFetcher { fetchSearchedMovies.searchMovies(query) }
        if (remoteData is Resource.Success) {
            return movieListDTOMapper(remoteData.data)
        }
        return emptyList()
    }
}