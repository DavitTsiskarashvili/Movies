package com.movies.domain.usecase.movies

import com.movies.common.network.CategoryType
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.MoviesRepository
import com.movies.domain.usecase.base.BaseUseCase

class GetMoviesUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<CategoryType, List<MovieDomainModel>>() {

    override suspend fun invoke(params: CategoryType?): List<MovieDomainModel> {
        return params?.let {
            moviesRepository.fetchMovies(it)
        } ?: emptyList()
    }

}