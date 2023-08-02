package com.movies.domain.usecase.search

import androidx.paging.PagingData
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.SearchRepository
import com.movies.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class SearchMoviesUseCase(
    private val searchRepository: SearchRepository
) : BaseUseCase<String, Flow<PagingData<MovieDomainModel>>>() {

    override suspend fun invoke(params: String?): Flow<PagingData<MovieDomainModel>> {
        return searchRepository.searchMovies(params!!)
    }

}