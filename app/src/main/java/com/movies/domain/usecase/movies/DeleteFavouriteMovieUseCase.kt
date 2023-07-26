package com.movies.domain.usecase.movies

import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.FavouriteMovieRepository
import com.movies.domain.usecase.base.BaseUseCase

class DeleteFavouriteMovieUseCase(
    private val favouriteMovieRepository: FavouriteMovieRepository
) : BaseUseCase<MovieDomainModel, Unit>() {

    override suspend fun invoke(params: MovieDomainModel?) {
        return favouriteMovieRepository.deleteFavouriteMovie(params!!)
    }
}