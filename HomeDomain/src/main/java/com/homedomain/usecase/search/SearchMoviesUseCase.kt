package com.homedomain.usecase.search

import androidx.paging.Pager
import com.commondomain.base.BaseUseCase
import com.commondomain.model.MovieDomainModel
import com.homedomain.repository.SearchRepository

class SearchMoviesUseCase(
    private val searchRepository: SearchRepository
) : BaseUseCase<String, Pager<Int, MovieDomainModel>>() {

    override suspend fun invoke(params: String?): Pager<Int,MovieDomainModel> {
        return searchRepository.searchMovies(params!!)
    }

}