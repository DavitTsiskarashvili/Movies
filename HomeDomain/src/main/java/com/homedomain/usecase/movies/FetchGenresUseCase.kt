package com.homedomain.usecase.movies

import com.commondomain.base.BaseUseCase
import com.homedomain.model.GenreDomainModel
import com.homedomain.repository.MoviesRepository

class FetchGenresUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<Unit, List<GenreDomainModel>>() {

    override suspend fun invoke(params: Unit?): List<GenreDomainModel> {
        return moviesRepository.fetchMovieGenre()
    }
}