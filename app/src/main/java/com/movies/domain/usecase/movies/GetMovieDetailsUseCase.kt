package com.movies.domain.usecase.movies

import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.MoviesRepository
import com.movies.domain.usecase.base.BaseUseCase

class GetMovieDetailsUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<Int, MovieDomainModel>() {
    override suspend fun invoke(params: Int?): MovieDomainModel {
        return moviesRepository.fetchMovieDetails(params!!)
    }
}