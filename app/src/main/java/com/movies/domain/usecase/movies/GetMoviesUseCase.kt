package com.movies.domain.usecase.movies

import androidx.paging.PagingData
import com.movies.common.network.CategoryType
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.MoviesRepository
import com.movies.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<CategoryType, Flow<PagingData<MovieDomainModel>>>() {

    override suspend fun invoke(params: CategoryType?): Flow<PagingData<MovieDomainModel>> {
        return moviesRepository.fetchMovies(params!!)
    }

}