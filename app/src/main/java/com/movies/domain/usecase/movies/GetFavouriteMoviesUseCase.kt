package com.movies.domain.usecase.movies

import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.FavouriteMovieRepository
import com.movies.domain.usecase.base.BaseUseCase

class GetFavouriteMoviesUseCase(
    private val favouriteMovieRepository: FavouriteMovieRepository
) : BaseUseCase<Unit, List<MovieDomainModel>>() {

    override suspend fun invoke(params: Unit?): List<MovieDomainModel> {
        return favouriteMovieRepository.getFavouriteMovies()
    }
}