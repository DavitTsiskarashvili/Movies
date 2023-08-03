package com.movies.domain.usecase.favourites

import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.FavouriteMovieRepository
import com.movies.domain.usecase.base.BaseUseCase

class UpdateFavouriteStatusMovieUseCase(
    private val favouriteMovieRepository: FavouriteMovieRepository
) : BaseUseCase<MovieDomainModel, Unit>() {

    override suspend fun invoke(params: MovieDomainModel?) {
        val isFavourite = favouriteMovieRepository.isFavouriteMovie(params!!.id)
        if (isFavourite) {
            favouriteMovieRepository.deleteFavouriteMovie(params)
        } else {
            favouriteMovieRepository.insertFavouriteMovie(params)
        }
    }
}