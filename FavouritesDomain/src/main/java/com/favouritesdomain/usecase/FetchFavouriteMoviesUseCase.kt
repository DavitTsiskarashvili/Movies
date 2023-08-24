package com.favouritesdomain.usecase

import com.commondomain.base.BaseUseCase
import com.commondomain.model.MovieDomainModel
import com.favouritesdomain.repository.FavouriteMovieRepository

class FetchFavouriteMoviesUseCase(
    private val favouriteMovieRepository: FavouriteMovieRepository
) : BaseUseCase<Unit, List<MovieDomainModel>>() {

    override suspend fun invoke(params: Unit?): List<MovieDomainModel> {
        return favouriteMovieRepository.getFavouriteMovies()
    }
}