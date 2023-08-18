package com.movies.domain.usecase.search

import androidx.paging.Pager
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.SearchRepository
import com.movies.domain.usecase.base.BaseUseCase

class SearchMoviesUseCase(
    private val searchRepository: SearchRepository
) : BaseUseCase<String, Pager<Int,MovieDomainModel>>() {

    override suspend fun invoke(params: String?): Pager<Int,MovieDomainModel> {
        return searchRepository.searchMovies(params!!)
    }

}