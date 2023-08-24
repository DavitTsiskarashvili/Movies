package com.homedomain.usecase.movies

import androidx.paging.Pager
import com.commondomain.base.BaseUseCase
import com.commondomain.model.MovieDomainModel
import com.homedomain.repository.MoviesRepository

class FetchMoviesUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<String, Pager<Int, MovieDomainModel>>() {

    override suspend fun invoke(params: String?): Pager<Int, MovieDomainModel> {
        return moviesRepository.fetchMovies(params!!)
    }

}