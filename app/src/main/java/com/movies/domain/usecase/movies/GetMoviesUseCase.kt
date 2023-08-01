package com.movies.domain.usecase.movies

import androidx.paging.PagingData
import com.movies.common.network.CategoryType
import com.movies.common.utils.LiveDataDelegate
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.MoviesRepository
import com.movies.domain.usecase.base.BaseUseCase

class GetMoviesUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<CategoryType, LiveDataDelegate<PagingData<MovieDomainModel>>>() {

    override suspend fun invoke(params: CategoryType?): LiveDataDelegate<PagingData<MovieDomainModel>> {
        return params.let {
            moviesRepository.fetchMovies(it!!)
        }
    }

}