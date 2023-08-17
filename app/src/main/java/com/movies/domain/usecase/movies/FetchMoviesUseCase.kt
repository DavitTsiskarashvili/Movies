package com.movies.domain.usecase.movies

import androidx.paging.Pager
import com.movies.common.network.CategoryType
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.MoviesRepository
import com.movies.domain.usecase.base.BaseUseCase

class FetchMoviesUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<CategoryType, Pager<Int, MovieDomainModel>>() {

    override suspend fun invoke(params: CategoryType?): Pager<Int, MovieDomainModel> {
        return moviesRepository.fetchMovies(params!!)
    }

}