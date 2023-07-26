package com.movies.domain.usecase.search

import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.SearchRepository
import com.movies.domain.usecase.base.BaseUseCase

class SearchMoviesUseCase(
    private val searchRepository: SearchRepository
) : BaseUseCase<String, List<MovieDomainModel>>() {

    override suspend fun invoke(params: String?): List<MovieDomainModel> {
        return params?.let {
            searchRepository.searchMovies(it)
        } ?: emptyList()
    }

}