package com.movies.domain.usecase.movies

import com.movies.domain.model.GenreDomainModel
import com.movies.domain.repository.MoviesRepository
import com.movies.domain.usecase.base.BaseUseCase

class GetGenresUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<Unit, List<GenreDomainModel>>() {

    override suspend fun invoke(params: Unit?): List<GenreDomainModel> {
        return moviesRepository.fetchMovieGenre()
    }
}