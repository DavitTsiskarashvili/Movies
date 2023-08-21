package com.detailsdomain.usecase

import com.commondomain.base.BaseUseCase
import com.commondomain.model.MovieDomainModel
import com.detailsdomain.repository.MovieDetailsRepository

class FetchMovieDetailsUseCase(
    private val moviesRepository: MovieDetailsRepository
) : BaseUseCase<Int, MovieDomainModel>() {
    override suspend fun invoke(params: Int?): MovieDomainModel {
        return moviesRepository.fetchMovieDetails(params!!)
    }
}