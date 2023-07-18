package com.movies.domain.usecase.movies

import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.MoviesRepository
import com.movies.domain.usecase.base.BaseUseCase

class GetMoviesUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<Unit, List<MovieDomainModel>>() {

    override suspend fun invoke(params: Unit?): List<MovieDomainModel> {
        return moviesRepository.fetchMovies()
    }

}