package com.homedomain.usecase.movies

import androidx.paging.Pager
import com.commondomain.base.BaseUseCase
import com.commondomain.model.MovieDomainModel
import com.homedomain.repository.MoviesRepository
import com.movies.common.network.CategoryType
import com.movies.domain.repository.MoviesRepository
import com.movies.domain.usecase.base.BaseUseCase

class FetchMoviesUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<CategoryType, Pager<Int, MovieDomainModel>>() {

    override suspend fun invoke(params: CategoryType?): Pager<Int, MovieDomainModel> {
        return moviesRepository.fetchMovies(params!!)
    }

}