package com.favouritesdomain.usecase

import com.commondomain.base.BaseUseCase
import com.commondomain.model.MovieDomainModel
import com.favouritesdomain.repository.FavouriteMovieRepository

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